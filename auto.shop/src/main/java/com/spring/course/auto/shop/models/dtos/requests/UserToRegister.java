package com.spring.course.auto.shop.models.dtos.requests;

import com.spring.course.auto.shop.models.enums.ERole;
import lombok.Data;
import lombok.RequiredArgsConstructor;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.util.Set;

@Data
@RequiredArgsConstructor
public class UserToRegister {
    @NotBlank(message = "Username should not be blank")
    @Size(min = 6, max = 20, message = "Invalid username length")
    private String username;

    @NotBlank(message = "Password should not be blank")
    @Size(min = 6, max = 40, message = "Invalid password length")
    private String password;

    @NotBlank(message = "Name should not be blank")
    @Size(min = 6, max = 40, message = "Invalid name length")
    private String name;

    private Set<ERole> roles;
}
