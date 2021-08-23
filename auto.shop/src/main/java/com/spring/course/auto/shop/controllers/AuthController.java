package com.spring.course.auto.shop.controllers;

import com.spring.course.auto.shop.models.dtos.requests.UserToLogin;
import com.spring.course.auto.shop.models.dtos.requests.UserToRegister;
import com.spring.course.auto.shop.models.dtos.responces.AuthenticatedUser;
import com.spring.course.auto.shop.models.dtos.responces.LoggedUser;
import com.spring.course.auto.shop.services.interfaces.IAuthService;
import com.spring.course.auto.shop.types.BadMessage;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.transaction.TransactionSystemException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.net.URI;

@CrossOrigin(origins = "*", maxAge = 3600)
@RequiredArgsConstructor
@RestController
@RequestMapping(value = "/api/auth", produces = MediaType.APPLICATION_JSON_VALUE)
public class AuthController {

    private final IAuthService authService;

    @PostMapping(value = "/register", consumes = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public ResponseEntity<?> register(@Valid @RequestBody UserToRegister userToRegister) {
        try {
            AuthenticatedUser authenticatedUser = authService.register(userToRegister);
            return ResponseEntity.created(URI.create("")).body(authenticatedUser);

        } catch (TransactionSystemException exception) {
            return ResponseEntity
                    .status(HttpStatus.SERVICE_UNAVAILABLE)
                    .body(new BadMessage(exception.getMessage()));
        } catch (IllegalArgumentException exception) {
            return ResponseEntity
                    .status(HttpStatus.BAD_REQUEST)
                    .body(new BadMessage(exception.getMessage()));
        } catch (Exception exception) {
            return ResponseEntity
                    .status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(new BadMessage(exception.getMessage()));
        }
    }

    //@PreAuthorize("hasRole('USER') or hasRole('MODERATOR')")
    @PostMapping(value = "/login", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> login(@Valid @RequestBody UserToLogin loginRequest) {
        try {
            LoggedUser loginResponse = authService.login(loginRequest);
            return ResponseEntity.ok(loginResponse);
        } catch (IllegalArgumentException exception) {
            return ResponseEntity
                    .status(HttpStatus.BAD_REQUEST)
                    .body(new BadMessage(exception.getMessage()));
        } catch (Exception exception) {
            return ResponseEntity
                    .status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(new BadMessage(exception.getMessage()));
        }
    }
}
