package ru.skypro.homework.service;

import org.springframework.security.core.Authentication;
import org.springframework.web.multipart.MultipartFile;
import ru.skypro.homework.dto.NewPassword;
import ru.skypro.homework.dto.UpdateUser;
import ru.skypro.homework.dto.UserDto;

import java.io.IOException;

public interface UserService {

    void newPassword(NewPassword newPassword, Authentication authentication);

    UserDto updateUserDto(UpdateUser updateUserDto, Authentication authentication);

    void updateUserImage(MultipartFile file, Authentication authentication) throws IOException;

    UserDto getAuthorizedUserDto(Authentication authentication);

    byte[] getUserImage(Integer userId) throws IOException;
}
