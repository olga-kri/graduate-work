package ru.skypro.homework.controller;


import io.swagger.v3.oas.annotations.Parameter;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import ru.skypro.homework.dto.NewPassword;
import ru.skypro.homework.dto.UpdateUser;
import ru.skypro.homework.dto.UserDto;
import ru.skypro.homework.service.UserService;


import javax.validation.Valid;
import java.io.IOException;


@Slf4j
@CrossOrigin(value = "http://localhost:3000")
@RestController
@RequestMapping("/users")
public class UsersController {

    private final UserService userService;

    public UsersController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping(value = "/set_password",
                 consumes = { "application/json" })
    public ResponseEntity<Void> setPassword(@Valid @RequestBody NewPassword newPassword, Authentication authentication) {
        userService.newPassword(newPassword,authentication);
        return ResponseEntity.status(
                (newPassword!=null) ?
                        HttpStatus.OK :
                        HttpStatus.UNAUTHORIZED
        ).build();
    }

    @GetMapping(value = "/me",
                produces = { "application/json" })
    public ResponseEntity<UserDto> getUser(Authentication authentication) {
        return ResponseEntity.ok(userService.getAuthorizedUserDto(authentication));
    }

    @GetMapping(value = "/{id}/image",
                produces = MediaType.IMAGE_PNG_VALUE)
    public ResponseEntity<byte[]> getImage(@PathVariable Integer id) throws IOException {
        return ResponseEntity.ok(userService.getUserImage(id));
    }

    @PatchMapping(value = "/me",
                  produces = { "application/json" },
                  consumes = { "application/json" })
    public ResponseEntity<UserDto> updateUser(
            @Parameter(name = "UpdateUserDto") @Valid @RequestBody(required = false) UpdateUser updateUser, Authentication authentication) {
        return ResponseEntity.ok(userService.updateUserDto(updateUser, authentication));
    }

    @PatchMapping(value = "/me/image",
                  consumes = { "multipart/form-data" })
    public ResponseEntity<Void> updateUserImage(
            @Parameter(name = "image",required = true) @RequestPart(value = "image", required = true) MultipartFile image, Authentication authentication) throws IOException {
        userService.updateUserImage(image, authentication);
        return ResponseEntity.ok().build();
    }
}