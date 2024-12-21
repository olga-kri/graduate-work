package ru.skypro.homework.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import ru.skypro.homework.dto.CommentDTO;
import ru.skypro.homework.dto.Comments;
import ru.skypro.homework.dto.CreateOrUpdateComment;


@Slf4j
@CrossOrigin(value = "http://localhost:3000")
@RestController
@RequiredArgsConstructor
@RequestMapping("/ads/{adId}/comments")
public class CommentController {

    @GetMapping
    public Comments getComments(@PathVariable("adId") Integer adId) {
        Comments comments = null;
        return comments;
    }

    @PostMapping
    public CommentDTO postComment(
            @PathVariable("adId") Integer adId,
            @RequestBody CreateOrUpdateComment createdComment) {
        CommentDTO commentDTO = null;
        return commentDTO;
    }

    @PatchMapping("/{commentId}")
    @Transactional
    @PreAuthorize("@commentService.hasPermission(#adId, #commentId)")
    public CommentDTO patchComment(
            @PathVariable("adId") Integer adId,
            @PathVariable("commentId") Integer commentId,
            @RequestBody CreateOrUpdateComment updatedComment) {

        CommentDTO commentDTO = null;
        return commentDTO;
    }

    @DeleteMapping("/{commentId}")
    @Transactional
    @PreAuthorize("@commentService.hasPermission(#adId, #commentId)")
    public void deleteComment(@PathVariable("adId") Integer adId,
                              @PathVariable("commentId") Integer commentId) {
      //  commentService.delete(adId, commentId);
    }

}
