package com.spring.course.auto.shop.controllers;

import com.spring.course.auto.shop.models.Comment;
import com.spring.course.auto.shop.models.entities.CommentEntity;
import com.spring.course.auto.shop.services.CommentService;
import com.spring.course.auto.shop.services.MappingManager;
import com.spring.course.auto.shop.types.BadMessage;
import com.spring.course.auto.shop.types.QueryResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping(path = "/api/announcements/{announcementId}/comments", produces = MediaType.APPLICATION_JSON_VALUE)
public class CommentController {
    private final CommentService commentService;
    private final MappingManager mappingManager;

    @GetMapping()
    public ResponseEntity<?> query(
            @Valid @NotNull @PathVariable(name = "announcementId") Long announcementId,
            @RequestParam(name = "page", required = false, defaultValue = "1") int page,
            @RequestParam(name = "pageSize", required = false, defaultValue = "10") int pageSize
    ) {
        Page<Comment> commentPage;
        page--;
        try {
            commentPage = this.commentService.query(PageRequest.of(page, pageSize), announcementId);
        } catch (IllegalArgumentException exception) {
            return ResponseEntity.badRequest().body(new BadMessage(exception.getMessage()));
        }
        page++;


        List<CommentEntity> commentEntities = this.mappingManager.mapToMessageEntities(commentPage.getContent());
        QueryResponse<CommentEntity> queryResponse = new QueryResponse<>();
        queryResponse.setPage(page);
        queryResponse.setPageSize(pageSize);
        queryResponse.setTotalPages(commentPage.getTotalPages());
        queryResponse.setValues(commentEntities);

        return ResponseEntity.ok().body(queryResponse);
    }

    @Valid
    @PostMapping()
    public ResponseEntity<?> postComment(@NotNull @RequestBody CommentEntity commentEntity) {
        try {
            this.commentService.postNewComment(this.mappingManager.mapToComment(commentEntity));
        } catch (IllegalArgumentException exception) {
            return ResponseEntity.badRequest().body(new BadMessage(exception.getMessage()));
        }

        return ResponseEntity.ok().body(null);
    }

    @Valid
    @PutMapping("{commentId}")
    public ResponseEntity<?> putComment(@PathVariable("commentId") Long id, @RequestBody CommentEntity commentEntity) {
        try {
            this.commentService.updateComment(id, this.mappingManager.mapToComment(commentEntity));
        } catch (IllegalArgumentException exception) {
            return ResponseEntity.badRequest().body(new BadMessage(exception.getMessage()));
        }

        return ResponseEntity.ok().body(null);
    }

    @Valid
    @DeleteMapping("{commentId}")
    public ResponseEntity<?> deleteComment(@PathVariable("commentId") @NotNull Long commentId) {
        try {
            this.commentService.deleteComment(commentId);
        } catch (IllegalArgumentException exception) {
            return ResponseEntity.badRequest().body(new BadMessage(exception.getMessage()));
        }

        return ResponseEntity.ok().body(null);
    }
}
