package ru.skypro.homework.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingConstants;
import org.mapstruct.MappingTarget;
import ru.skypro.homework.dto.AdDTO;
import ru.skypro.homework.dto.CreateOrUpdateAd;
import ru.skypro.homework.dto.ExtendedAd;
import ru.skypro.homework.entity.Ad;

import java.util.Collection;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING)
public interface AdMapper {

    @Mapping(source = "id",target = "pk")
    @Mapping(source = "author.id",target = "author")
    @Mapping(target = "image",expression = "java(imageMapper(ad))")
    AdDTO adToAdsDTO(Ad ad);

    @Mapping(target = "authorFirstName", source="author.firstName")
    @Mapping(target = "authorLastName", source="author.lastName")
    @Mapping(target = "email", source="author.email")
    @Mapping(target = "phone", source = "author.phone")
    @Mapping(target = "pk", source = "id")
    @Mapping(target = "image",  expression = "java( mapImage(ad) )")
    ExtendedAd mapExtended(Ad ad);

    Ad adsToAd(CreateOrUpdateAd createOrUpdateAd);

    Collection<AdDTO> adsToAdsListDto(Collection<Ad> adsCollection);

     default String imageMapper(Ad ad){
        return "/ads/" + ad.getId() + "/image";
    }

    void updateAds(CreateOrUpdateAd createAdsDTO, @MappingTarget Ad ad);
}
