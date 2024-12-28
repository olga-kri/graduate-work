package ru.skypro.homework.service.impl;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import ru.skypro.homework.entity.Image;
import ru.skypro.homework.repository.ImageRepository;
import ru.skypro.homework.service.ImageService;

import java.io.IOException;

@Service
@RequiredArgsConstructor
@Slf4j
public class ImageServiceImpl implements ImageService {

    private final ImageRepository imageRepository;

    @Override
    public Image downloadImage(MultipartFile file) throws IOException {
        Image image = new Image();
        image.setMediaType(file.getContentType());
        image.setData(file.getBytes());
        return imageRepository.save(image);
    }

    @Override
    public byte[] getImage(Integer id) {
        return new byte[0];
    }

    @Override
    public void deleteImage(Integer id) {
        imageRepository.deleteById(Long.valueOf(id));
    }
}
