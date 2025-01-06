package ru.skypro.homework.mapper;

import org.mapstruct.*;
import ru.skypro.homework.dto.*;
import ru.skypro.homework.entity.Image;
import ru.skypro.homework.entity.User;

import static org.aspectj.weaver.tools.cache.SimpleCacheFactory.path;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface UserMapper {

    @Mapping(target = "image", expression = "java(imageMapper(entity))")
    UserDto userToUserDto(User entity);

    default String imageMapper(User user){
        return "/users/"+ user.getId() + "/image";
    }

    // @Mapping(target = "id", ignore = true)
    @Mapping(target = "image", expression = "java(pathToImage(userdto))")
    User userDtoToUser(UserDto userdto);

    @Mapping(source = "username", target = "email")
    User userFromRegisterDto(Register register);

    void updateUser(UpdateUser updateUser, @MappingTarget User currentUser);

    @Mapping(source = "username", target = "email")
    User loginDtoToUser(Login login);

    default Image pathToImage(UserDto userdto) {
        String imagePath = userdto.getImage();
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
        return image;
    }

    private Integer extractIdFromPath(String imagePath){
        try {
            String[] parts = path.split("/");
            return Integer.valueOf(parts[2]);
        } catch (Exception e) {
            throw new IllegalArgumentException("Invalid path format: " + path, e);
        }
    }
    default String map(byte[] image) {
        return image != null ? new String(image) : null;
    }

    default byte[] map(String image) {
        return image != null ? image.getBytes() : null;
    }

    SecurityDto toSecurityDto(User user);

}
