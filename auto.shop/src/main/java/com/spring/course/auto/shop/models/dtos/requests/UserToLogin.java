package com.spring.course.auto.shop.models.dtos.requests;

import lombok.AllArgsConstructor;
import lombok.Data;

import javax.validation.constraints.NotBlank;

@Data
@AllArgsConstructor
public class UserToLogin {
    @NotBlank
    private String username;

    @NotBlank
    private String password;
}
