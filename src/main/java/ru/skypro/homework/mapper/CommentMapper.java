package ru.skypro.homework.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingConstants;
import ru.skypro.homework.dto.CommentDto;
import ru.skypro.homework.dto.CreateOrUpdateComment;
import ru.skypro.homework.entity.Comment;
import ru.skypro.homework.entity.Image;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZoneOffset;
import java.util.Collection;
import java.util.List;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING)
public interface CommentMapper {
    @Mapping(source = "author.id", target = "author")
    @Mapping(target = "authorImage", expression = "java(imageFromDto(comment))")
    @Mapping(source = "author.firstName", target = "authorFirstName")
    @Mapping(source = "id", target = "pk")
    CommentDto toCommentDto(Comment comment);

    @Mapping(source = "pk", target = "id", ignore = true)
    @Mapping(source = "author", target = "author.id")
    @Mapping(source = "authorFirstName", target = "author.firstName")
    @Mapping(target = "author.image.id", expression = "java(pathToImageFromDto(commentDto))")
    Comment fromCommentDto(CommentDto dto);

    Comment toComment(CreateOrUpdateComment createComment);

    List<CommentDto> toCommentsListDto(Collection<Comment> commentCollection);

    default String imageFromDto(Comment comment) {
        int id = comment.getId();
        return "/comments/" + id + "/image";
    }

    default LocalDateTime map(Long timestamp) {
        if (timestamp == null) {
            return null;
        }
        Instant instant = Instant.ofEpochMilli(timestamp);
        ZoneId zoneId = ZoneId.systemDefault();
        return instant.atZone(zoneId).toLocalDateTime();
    }

    default Long map(LocalDateTime dateTime) {
        ZoneId zoneId = ZoneId.systemDefault();
        Instant instant = dateTime.atZone(zoneId).toInstant();
        return instant.toEpochMilli();
    }
    default Integer pathToImageFromDto(CommentDto dto) {
        String imagePath = dto.getAuthorImage();
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
        return image.getId();
    }

    private Integer extractIdFromPath(String path) {
        try {
            String[] parts = path.split("/");
            return Integer.valueOf(parts[2]);
        } catch (Exception e) {
            throw new IllegalArgumentException("Invalid path format: " + path, e);
        }
    }

    default byte[] map(String image) {
        return image != null ? image.getBytes() : null;
    }


}
