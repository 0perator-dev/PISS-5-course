package com.spring.course.auto.shop.models.dtos.responces;

import com.spring.course.auto.shop.models.Role;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.Set;

@Data
@AllArgsConstructor
public class UserDetails {
    private String username;
    private String name;
    private Set<Role> role;
}
