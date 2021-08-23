package com.spring.course.auto.shop.models.dtos.responces;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class LoggedUser {
    private String token;
    private AuthenticatedUser authenticatedUser;
}
