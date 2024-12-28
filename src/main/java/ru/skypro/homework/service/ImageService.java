package ru.skypro.homework.service;

import org.springframework.web.multipart.MultipartFile;
import ru.skypro.homework.entity.Image;

import java.io.IOException;

public interface ImageService {

    Image downloadImage(MultipartFile file) throws IOException;

    byte[] getImage(Integer id);

    void deleteImage(Integer id);

}
