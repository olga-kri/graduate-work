package ru.skypro.homework.service.impl;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import ru.skypro.homework.dto.NewPassword;
import ru.skypro.homework.dto.UpdateUser;
import ru.skypro.homework.dto.UserDto;
import ru.skypro.homework.entity.Image;
import ru.skypro.homework.entity.User;
import ru.skypro.homework.exception.UserNotFoundException;
import ru.skypro.homework.mapper.UserMapper;
import ru.skypro.homework.repository.UserRepository;
import ru.skypro.homework.service.ImageService;
import ru.skypro.homework.service.UserService;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;

/**
 * Реализация сервиса для управления пользователями.
 * Реализует методы для получения информации о пользователе, обновления данных пользователя,
 * смены пароля и управления изображением пользователя.
 */

@Service
@RequiredArgsConstructor
@Slf4j
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final UserMapper mapper;
    private final PasswordEncoder encoder;
    private final ImageService imageService;

    /**
     * Обновляет пароль пользователя.
     * @param newPassword DTO с новыми данными пароля.
     * @param authentication информация о текущем пользователе.
     * @throws UserNotFoundException если пользователь не найден.
     */
    @Override
    public void newPassword(NewPassword newPassword, Authentication authentication) {
        User user = userRepository.findUserByEmailIgnoreCase(authentication.getName()).orElseThrow(UserNotFoundException::new);
        user.setPassword(encoder.encode(newPassword.getNewPassword()));
        userRepository.save(user);
        mapper.userToUserDto(user);
    }

    /**
     * Обновляет информацию о пользователе.
     * @param updateUserDto DTO с новыми данными пользователя.
     * @param authentication информация о текущем пользователе.
     * @return DTO обновленного пользователя.
     * @throws UserNotFoundException если пользователь не найден.
     */
    @Override
    public UserDto updateUserDto(UpdateUser updateUserDto, Authentication authentication) {
        User user = userRepository.findUserByEmailIgnoreCase(authentication.getName()).orElseThrow(UserNotFoundException::new);
        user.setFirstName(updateUserDto.getFirstName());
        user.setLastName(updateUserDto.getLastName());
        user.setPhone(updateUserDto.getPhone());
        userRepository.save(user);
        return mapper.userToUserDto(user);
    }

    /**
     * Обновляет изображение пользователя.
     * @param file файл изображения, которое нужно загрузить.
     * @param authentication информация о текущем пользователе.
     * @throws UserNotFoundException если пользователь не найден.
     * @throws RuntimeException если произошла ошибка при загрузке изображения.
     */
    @Override
    public void updateUserImage(MultipartFile file, Authentication authentication) throws IOException {
        User user = userRepository.findUserByEmailIgnoreCase(authentication.getName())
                .orElseThrow(UserNotFoundException::new);
        try {
            Image image = imageService.downloadImage(file);
            user.setImage(image);
            userRepository.save(user);
        } catch (IOException e) {
            throw new RuntimeException("Failed to update user's image", e);
        }
    }

    /**
     * Получает информацию о текущем авторизованном пользователе.
     * @param authentication информация о текущем пользователе.
     * @return DTO текущего авторизованного пользователя.
     * @throws UserNotFoundException если пользователь не найден.
     */
    @Override
    public UserDto getAuthorizedUserDto(Authentication authentication) {
        return mapper.userToUserDto(userRepository.findUserByEmailIgnoreCase(authentication.getName()).orElseThrow(UserNotFoundException::new));
    }

    /**
     * Получает изображение пользователя по его идентификатору.
     * Либо, если пользователь не загрузил фото, возвращает фото аватара по умолчанию
     * из файла "src/main/resources/empthyPhoto.png"
     * @param userId идентификатор пользователя.
     * @return массив байтов, представляющий изображение пользователя.
     * @throws IOException если произошла ошибка при чтении изображения.
     * @throws UserNotFoundException если пользователь не найден.
     */
    @Override
    public byte[] getUserImage(Integer userId) throws IOException {
        User user = userRepository.findById(userId).orElseThrow(UserNotFoundException::new);
        if (user.getImage() != null) {
            return user.getImage().getData();
        } else {
            File empty = new File("src/main/resources/empthyPhoto.png");
            return Files.readAllBytes(empty.toPath());
        }
    }

}
