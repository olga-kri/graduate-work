package ru.skypro.homework.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingConstants;
import org.mapstruct.MappingTarget;
import ru.skypro.homework.dto.AdDto;
import ru.skypro.homework.dto.CreateOrUpdateAd;
import ru.skypro.homework.dto.ExtendedAd;
import ru.skypro.homework.entity.Ad;
import ru.skypro.homework.entity.Image;

import java.util.Collection;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING)
public interface AdMapper {

    @Mapping(source = "id",target = "pk")
    @Mapping(source = "author.id",target = "author")
    @Mapping(target = "image",expression = "java(imageMapper(ad))")
    AdDto adToAdsDTO(Ad ad);

  // @Mapping(source = "author", target = "author.id")
  // @Mapping(source = "pk", target = "id")
    // @Mapping(target = "image.id",expression = "java(pathToImage(adDTO))")
  //  Ad fromAdDto(AdDTO dto);

    @Mapping(target = "authorFirstName", source="author.firstName")
    @Mapping(target = "authorLastName", source="author.lastName")
    @Mapping(target = "email", source="author.email")
    @Mapping(target = "phone", source = "author.phone")
    @Mapping(target = "pk", source = "id")
    @Mapping(target = "image",  expression = "java( mapImage(ad) )")
    ExtendedAd mapExtended(Ad ad);

    Ad adsToAd(CreateOrUpdateAd createOrUpdateAd);

    Collection<AdDto> adsToAdsListDto(Collection<Ad> adsCollection);

     default String imageMapper(Ad ad){
        return "/ads/" + ad.getId() + "/image";
    }

    void updateAds(CreateOrUpdateAd createAdsDTO, @MappingTarget Ad ad);

     default Integer pathToImage(AdDto adDto) {
        String imagePath = adDto.getImage();
        if (imagePath == null || imagePath.isEmpty()) {
            return null;
        }
        Image image = new Image();
        try {
            Integer id = extractIdFromPath(imagePath);
            image.setId(id);
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException("Invalid image path: " + imagePath, e);
        }
        return image.getId();
    }

    private Integer extractIdFromPath(String path) {
        try {
            String[] parts = path.split("/");
            return Integer.valueOf(parts[2]); // Получаем id из пути
        } catch (Exception e) {
            throw new IllegalArgumentException("Invalid path format: " + path, e);
        }
    }
    default String mapImage(Ad ad) {
        Integer id = ad.getId();
        return "/ads/" + id + "/image";
    }
    default String map(byte[] image) {
        return image != null ? new String(image) : null;
    }

    default byte[] map(String image) {
        return image != null ? image.getBytes() : null;
    }
}
