package ru.skypro.homework.service.impl;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import ru.skypro.homework.entity.Image;
import ru.skypro.homework.exception.ImageNotFoundException;
import ru.skypro.homework.repository.ImageRepository;
import ru.skypro.homework.service.ImageService;

import java.io.IOException;

/**
 * Реализация сервиса для работы с изображениями.
 * Предоставляет методы для загрузки, удаления и получения изображений.
 */

@Service
@RequiredArgsConstructor
@Slf4j
public class ImageServiceImpl implements ImageService {

    private final ImageRepository imageRepository;

    /**
     * Загружает изображение в хранилище и сохраняет его в базе данных.
     * @param file файл изображения, который нужно загрузить.
     * @return объект {@link Image}, содержащий информацию о загруженном изображении.
     * @throws IOException если произошла ошибка при чтении данных из файла.
     */
    @Override
    public Image downloadImage(MultipartFile file) throws IOException {
        log.info("Request to avatar upload");
        Image image = new Image();
        image.setMediaType(file.getContentType());
        image.setData(file.getBytes());
        return imageRepository.save(image);
    }

    /**
     * Получает изображение по его идентификатору.
     * @param id идентификатор изображения.
     * @return массив байтов, представляющий данные изображения.
     * @throws RuntimeException если изображение с указанным идентификатором не найдено.
     */
    @Override
    public byte[] getImage(Integer id) {
        byte[] data;
        data = imageRepository.findById(Long.valueOf(id)).orElseThrow(ImageNotFoundException::new).getData();
        return data;
    }

    /**
     * Удаляет изображение по его идентификатору.
     * @param id идентификатор изображения, которое нужно удалить.
     */
    @Override
    public void deleteImage(Integer id) {
        log.info("Request to avatar delete by id {}", id);
        imageRepository.deleteById(Long.valueOf(id));
    }
}
