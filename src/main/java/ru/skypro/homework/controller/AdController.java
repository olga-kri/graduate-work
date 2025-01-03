package ru.skypro.homework.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import ru.skypro.homework.dto.AdDTO;
import ru.skypro.homework.dto.CreateOrUpdateAd;
import ru.skypro.homework.dto.Ads;
import ru.skypro.homework.dto.ExtendedAd;


import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.util.List;

@Slf4j
@CrossOrigin(value = "http://localhost:3000")
@RequiredArgsConstructor
@RestController
@RequestMapping("/ads")
public class AdController {

    @GetMapping("/{id}")
    public ExtendedAd get(@PathVariable Integer id) {
        ExtendedAd extendedAd = null;
        return extendedAd;
    }

    @GetMapping("/my")
    public List <AdDTO> getMyAds() {
        List <AdDTO> adDTOList = List.of();
        return adDTOList;
    }

    @GetMapping
    public Ads getAll() {
        Ads ads = null;
        return ads;
    }

    @PostMapping(consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    @ResponseStatus(HttpStatus.CREATED)
    @Transactional
    public AdDTO create(
            @Valid @RequestPart CreateOrUpdateAd properties,
            @RequestPart MultipartFile image) {
        AdDTO adDTO = null;
        return adDTO;
    }

    @PatchMapping("/{id}")
    @Transactional
    @PreAuthorize("@userService.hasPermission(@adService.getAd(#id))")
    public AdDTO patchProperties(
            @NotNull @PathVariable Integer id,
            @Valid @RequestBody CreateOrUpdateAd properties) {
        AdDTO adDTO = null;
        return adDTO;
    }

    @PatchMapping(path="/{id}/image", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    @Transactional
    @PreAuthorize("@userService.hasPermission(@adService.getAd(#id))")
    public AdDTO patchImg(
            @NotNull @PathVariable Integer id,
            @RequestPart MultipartFile image) {
        AdDTO adDTO = null;
        return adDTO;
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @Transactional
    @PreAuthorize("@userService.hasPermission(@adService.getAd(#id))")
    public void delete(@NotNull @PathVariable Integer id) {
        //adService.delete(id);
    }
}