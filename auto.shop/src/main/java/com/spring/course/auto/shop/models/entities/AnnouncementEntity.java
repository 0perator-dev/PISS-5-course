package com.spring.course.auto.shop.models.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AnnouncementEntity {
    private Long id;

    @NotBlank
    @Size(max = 60)
    private String title;

    @Size(max = 255)
    private String description;

    @NotNull(message = "Reference Entity is required property")
    private ReferenceEntity owner;
}
