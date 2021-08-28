package com.spring.course.auto.shop.services;

import com.spring.course.auto.shop.models.Comment;
import com.spring.course.auto.shop.repositories.IAnnouncementRepository;
import com.spring.course.auto.shop.repositories.ICommentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

@Service
@RequiredArgsConstructor
public class CommentService {
    private final ICommentRepository commentRepository;
    private final IAnnouncementRepository announcementRepository;

    @Valid
    public Page<Comment> query(Pageable pageable, @NotNull Long announcementId) {
        isAnnouncementExist(announcementId);
        return this.commentRepository.findAllByAnnouncementId(pageable, announcementId);
    }

    @Valid
    public void postNewComment(@NotNull Comment comment) throws IllegalArgumentException {
        if(comment.getId() != null) {
            isCommentExist(comment.getId());
        }

        this.commentRepository.save(comment);
    }

    @Valid
    public void updateComment(@NotNull Comment comment) throws IllegalArgumentException {
        isCommentExist(comment.getId());

        this.commentRepository.save(comment);
    }

    @Valid
    public void deleteComment(@NotNull Long commentId) throws IllegalArgumentException {
        isCommentExist(commentId);

        this.commentRepository.deleteById(commentId);
    }

    private boolean isAnnouncementExist(Long announcementId) throws IllegalArgumentException {
        if (this.announcementRepository.existsById(announcementId)) {
            return true;
        }

        throw new IllegalArgumentException("Announcement with id " + announcementId + " doesn't exist.");
    }

    private boolean isCommentExist(Long commentId) throws IllegalArgumentException {
        if (this.commentRepository.existsById(commentId)) {
            return true;
        }

        throw new IllegalArgumentException("Comment with id " + commentId + " already exist");
    }
}
