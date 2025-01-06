package ru.skypro.homework.service.impl;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import ru.skypro.homework.dto.AdDto;
import ru.skypro.homework.dto.Ads;
import ru.skypro.homework.dto.CreateOrUpdateAd;
import ru.skypro.homework.dto.ExtendedAd;
import ru.skypro.homework.entity.Ad;
import ru.skypro.homework.entity.Image;
import ru.skypro.homework.entity.User;
import ru.skypro.homework.exception.AdNotFoundException;
import ru.skypro.homework.exception.UserNotFoundException;
import ru.skypro.homework.mapper.AdMapper;
import ru.skypro.homework.repository.AdRepository;
import ru.skypro.homework.repository.UserRepository;
import ru.skypro.homework.service.AdService;
import ru.skypro.homework.service.CommentService;
import ru.skypro.homework.service.ImageService;
import javax.transaction.Transactional;
import java.io.IOException;
import java.util.Collection;
import java.util.stream.Collectors;

import static org.aspectj.util.LangUtil.isEmpty;


@Service
@RequiredArgsConstructor
@Slf4j
public class AdServiceImpl implements AdService {

    private final AdRepository adRepository;
    private final AdMapper adMapper;
    private final ImageService imageService;
    private final UserRepository userRepository;
    private final CommentService commentService;

    @Override
    public AdDto createAd(CreateOrUpdateAd createOrUpdateAd, MultipartFile file, Authentication authentication) throws IOException {
        if (createOrUpdateAd.getPrice() < 0) {
            throw new IllegalArgumentException("Price cannot be negative");
        }
        User user = userRepository.findUserByEmailIgnoreCase(authentication.getName()).orElseThrow(UserNotFoundException::new);
        log.info("Request to create new ad");
        Ad ad = new Ad();
        ad.setTitle(createOrUpdateAd.getTitle());
        ad.setPrice(createOrUpdateAd.getPrice());
        ad.setDescription(createOrUpdateAd.getDescription());
        Image image;
        try {
            image = imageService.downloadImage(file);
        } catch (IOException e) {
            throw new RuntimeException("Photo is not downloaded");
        }
        ad.setImage(image);
        ad.setAuthor(user);
        Ad savedAd = adRepository.save(ad);
        log.info("Save new ad ID:" + ad.getId());
        return adMapper.adToAdsDTO(savedAd);
    }

    @Override
    public ExtendedAd getAdById(Integer id) {
        log.info("Request to get full info about ad");
        return adRepository.findById(id).map(adMapper::mapExtended).orElseThrow(AdNotFoundException::new);
    }

    @Override
    public Ads getAllMyAds(Authentication authentication) {
        User user = userRepository.findUserByEmailIgnoreCase(authentication.getName()).orElseThrow(UserNotFoundException::new);
        Ads adsDto = new Ads();
        adsDto.setResults(user.getAds().stream().map(adMapper::adToAdsDTO).collect(Collectors.toList()));
        return adsDto;
    }

    @Override
    public Ads getAllAds() {
       Ads ads = new Ads();
       ads.setResults(adRepository.findAll().stream().map(adMapper::adToAdsDTO).collect(Collectors.toList()));
       ads.setCount((int) adRepository.count());
       return ads;
  }

    @Transactional
    @Override
    public void removeAd(Integer id, Authentication authentication) {
        Ad ad = adRepository.findById(id).orElseThrow(AdNotFoundException::new);
        commentService.deleteAllByAdId(id);
        adRepository.deleteById(id);
        imageService.deleteImage(ad.getImage().getId());
    }

    @Override
    public AdDto updateAd(CreateOrUpdateAd createOrUpdateAdDto, Authentication authentication, Integer id) {
        if (createOrUpdateAdDto.getPrice() < 0) {
            throw new IllegalArgumentException("Price cannot be negative");
        }
        Ad ad = adRepository.findById(id).orElseThrow(AdNotFoundException::new);
        ad.setPrice(createOrUpdateAdDto.getPrice());
        ad.setTitle(createOrUpdateAdDto.getTitle());
        ad.setDescription(createOrUpdateAdDto.getDescription());
        adRepository.save(ad);
        return adMapper.adToAdsDTO(ad);
    }

    @Transactional
    @Override
    public AdDto updateImage(MultipartFile file, Authentication authentication, Integer id) throws IOException {
        log.info("Request to update image");
        Ad updateAdImage = adRepository.findById(id).orElseThrow(AdNotFoundException::new);
        Integer previousImageId = updateAdImage.getImage().getId();
        imageService.deleteImage(previousImageId);
        try {
            updateAdImage.setImage(imageService.downloadImage(file));
        } catch (IOException e) {
            throw new RuntimeException("Unable to download image");
        }
        adRepository.save(updateAdImage);
        return adMapper.adToAdsDTO(updateAdImage);
    }

    @Override
    public Ad getAd(Integer id) {
        return adRepository.findById(id).orElseThrow(AdNotFoundException::new);
    }

    @Override
    public byte[] getAdImage(Integer adId) {
        Ad ad = adRepository.findById(adId).orElseThrow(AdNotFoundException::new);
        return ad.getImage().getData();
    }
}
