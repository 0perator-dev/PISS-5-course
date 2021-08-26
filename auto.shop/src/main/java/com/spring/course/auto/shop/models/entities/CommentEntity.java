package com.spring.course.auto.shop.models.entities;

import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Data
public class CommentEntity {
    private Long id;

    @NotBlank
    @Size(max = 255)
    private String message;

    @NotNull
    private Long announcementId;

    @NotNull
    private Long userId;
}
