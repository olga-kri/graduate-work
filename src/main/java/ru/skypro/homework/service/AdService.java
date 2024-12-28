package ru.skypro.homework.service;

import org.springframework.security.core.Authentication;
import org.springframework.web.multipart.MultipartFile;

import ru.skypro.homework.dto.AdDto;
import ru.skypro.homework.dto.Ads;
import ru.skypro.homework.dto.CreateOrUpdateAd;
import ru.skypro.homework.dto.ExtendedAd;
import ru.skypro.homework.entity.Ad;

import java.io.IOException;

public interface AdService {
    AdDto createAd(CreateOrUpdateAd createOrUpdateAd, MultipartFile file, Authentication authentication) throws IOException;

    ExtendedAd getAdById(Integer id);

    Ads getAllMyAds(Authentication authentication);

    Ads getAllAds();

    void removeAd(Integer id, Authentication authentication);

    AdDto updateAd(CreateOrUpdateAd createOrUpdateAdDto, Authentication authentication, Integer id);

    byte[] updateImage(MultipartFile file, Authentication authentication, Integer id) throws IOException;

    Ad getAd(Integer id);

    byte[] getAdImage(Integer adId);
}
