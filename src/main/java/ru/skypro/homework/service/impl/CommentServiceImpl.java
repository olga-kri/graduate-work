package ru.skypro.homework.service.impl;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;
import ru.skypro.homework.dto.CommentDto;
import ru.skypro.homework.dto.Comments;
import ru.skypro.homework.dto.CreateOrUpdateComment;
import ru.skypro.homework.entity.Ad;
import ru.skypro.homework.entity.Comment;
import ru.skypro.homework.entity.User;
import ru.skypro.homework.exception.AdNotFoundException;
import ru.skypro.homework.exception.CommentNotFoundException;
import ru.skypro.homework.exception.UserNotFoundException;
import ru.skypro.homework.mapper.CommentMapper;
import ru.skypro.homework.repository.AdRepository;
import ru.skypro.homework.repository.CommentRepository;
import ru.skypro.homework.repository.UserRepository;
import ru.skypro.homework.service.CommentService;


import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Slf4j
public class CommentServiceImpl implements CommentService {

    private final CommentRepository commentRepository;
    private final AdRepository adRepository;
    private final UserRepository userRepository;
    private final CommentMapper commentMapper;

    @Override
    public CommentDto addComment(Integer adId, CreateOrUpdateComment createOrUpdateComment, Authentication authentication) {
        Ad ad = adRepository.findById(adId).orElseThrow(AdNotFoundException::new);
        Comment comment = new Comment();
        comment.setAd(ad);
        comment.setText(createOrUpdateComment.getText());
        comment.setCreatedAt(LocalDateTime.now());
        User currentUser = userRepository.findUserByEmailIgnoreCase(authentication.getName())
                .orElseThrow(UserNotFoundException::new);
        comment.setAuthor(currentUser);
        Comment savedComment = commentRepository.save(comment);
        return commentMapper.toCommentDto(savedComment);
    }

    @Override
    public Comments getComments(Integer adId) {
        Ad ad = adRepository.findById(adId).orElseThrow(AdNotFoundException::new);
        List<Comment> comments = ad.getComments();
        Comments commentsDto = null;
        commentsDto.setCount(comments.size());
        commentsDto.setResults(comments.stream()
                .map(commentMapper::toCommentDto)
                .collect(Collectors.toList()));
        return commentsDto;
    }

    @Override
    public Comment getComment(Integer commentId) {
        return commentRepository.findById(commentId).orElseThrow(CommentNotFoundException::new);
    }

    @Override
    public CommentDto updateComment(Integer adId, Integer commentId, CreateOrUpdateComment createOrUpdateCommentDto) {
        Comment comment = commentRepository.findById(commentId).orElseThrow(CommentNotFoundException::new);
        comment.setText(createOrUpdateCommentDto.getText());
        Comment updatedComment = commentRepository.save(comment);
        return commentMapper.toCommentDto(updatedComment);
    }

    @Override
    public void deleteComment(Integer adId, Integer commentId) {
        Comment comment = commentRepository.findById(commentId).orElseThrow(CommentNotFoundException::new);
        Ad ad = adRepository.findById(adId).orElseThrow(AdNotFoundException::new);
        ad.getComments().remove(comment);
        commentRepository.delete(comment);
    }

    @Override
    public void deleteAllByAdId(Integer adId) {
        commentRepository.deleteAllByAdId(adId);
    }

    @Override
    public byte[] getUserImage(Integer commentId) throws IOException {
        Comment comment = commentRepository.findById(commentId).orElseThrow(UserNotFoundException::new);
        if (comment.getAuthor() != null) {
            return comment.getAuthor().getImage().getData();
        } else {
         File emptyAvatar = new File("src/main/resources/empthyPhoto.png");
          return Files.readAllBytes(emptyAvatar.toPath());
         }

     }
}
