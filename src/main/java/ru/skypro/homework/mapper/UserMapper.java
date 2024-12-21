package ru.skypro.homework.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingConstants;
import org.mapstruct.MappingTarget;
import ru.skypro.homework.dto.Login;
import ru.skypro.homework.dto.Register;
import ru.skypro.homework.dto.UpdateUser;
import ru.skypro.homework.dto.UserDTO;
import ru.skypro.homework.entity.User;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING)
public interface UserMapper {

    @Mapping(target = "image",expression = "java(imageMapper(entity))")
    UserDTO userToUserDTO(User entity);

    default String imageMapper(User user){
        return "/users/"+ user.getId() + "/image";
    }

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "role", defaultValue = "USER")
    @Mapping(target = "image", source = "image")
    User userDtoToUser(UserDTO dto);

    @Mapping(source = "username", target = "email")
     User userFromRegisterDto(Register register);

    void updateUser(UpdateUser updateUser, @MappingTarget User currentUser);

    User loginDtoToUser(Login login);

}
