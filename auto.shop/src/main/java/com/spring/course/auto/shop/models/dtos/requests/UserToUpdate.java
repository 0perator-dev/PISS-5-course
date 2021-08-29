package com.spring.course.auto.shop.models.dtos.requests;

import lombok.Data;
import lombok.RequiredArgsConstructor;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@Data
@RequiredArgsConstructor
public class UserToUpdate {
    @NotBlank(message = "Username should not be blank")
    @Size(min = 6, max = 20, message = "Invalid username length")
    private String username;

    @NotBlank(message = "Password should not be blank")
    @Size(min = 6, max = 40, message = "Invalid password length")
    private String password;

    @NotBlank(message = "Name should not be blank")
    @Size(min = 6, max = 40, message = "Invalid name length")
    private String name;
}
