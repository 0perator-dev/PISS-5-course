package com.spring.course.auto.shop.models.dtos.responces;

import com.spring.course.auto.shop.models.Role;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.Set;

@Data
@AllArgsConstructor
public class AuthenticatedUser {
    private Long id;
    private String username;
    private Set<String> roles;
}
