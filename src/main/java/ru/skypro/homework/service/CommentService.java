package ru.skypro.homework.service;

import org.springframework.security.core.Authentication;
import ru.skypro.homework.dto.CommentDto;
import ru.skypro.homework.dto.Comments;
import ru.skypro.homework.dto.CreateOrUpdateComment;
import ru.skypro.homework.entity.Comment;

import java.io.IOException;

public interface CommentService {

    CommentDto addComment(Integer adId, CreateOrUpdateComment createOrUpdateComment, Authentication authentication);

    Comments getComments(Integer adId);

    Comment getComment(Integer commentId);

    CommentDto updateComment(Integer adId, Integer commentId, CreateOrUpdateComment createOrUpdateCommentDto);

    void deleteComment(Integer adId, Integer commentId);

    void deleteAllByAdId(Integer adId);

    byte[] getUserImage(Integer commentId) throws IOException;
}
