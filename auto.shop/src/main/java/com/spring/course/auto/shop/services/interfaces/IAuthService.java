package com.spring.course.auto.shop.services.interfaces;

import com.spring.course.auto.shop.models.dtos.requests.UserToLogin;
import com.spring.course.auto.shop.models.dtos.requests.UserToRegister;
import com.spring.course.auto.shop.models.dtos.responces.LoggedUser;
import com.spring.course.auto.shop.models.dtos.responces.AuthenticatedUser;

public interface IAuthService {
    AuthenticatedUser register(UserToRegister userToRegister);

    LoggedUser login(UserToLogin userToLogin);
}
