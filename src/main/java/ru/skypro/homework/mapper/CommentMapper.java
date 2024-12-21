package ru.skypro.homework.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingConstants;
import ru.skypro.homework.dto.CommentDTO;
import ru.skypro.homework.dto.CreateOrUpdateComment;
import ru.skypro.homework.entity.Comment;

import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.util.Collection;
import java.util.List;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING)
public interface CommentMapper {
    @Mapping(source = "author.id", target = "author")
    @Mapping(target = "authorImage", expression = "java(image(comment))")
    @Mapping(source = "author.firstName", target = "authorFirstName")
    @Mapping(source = "id", target = "pk")
    CommentDTO toCommentDTO(Comment comment);

    Comment toComment(CreateOrUpdateComment createComment);

    List<CommentDTO> toCommentsListDto(Collection<Comment> commentCollection);

    default Long createdAt(LocalDateTime value) {
        if (value == null) {
            return 0L;
        }
        return value.toInstant(ZoneOffset.ofHours(3)).toEpochMilli();
    }


}
