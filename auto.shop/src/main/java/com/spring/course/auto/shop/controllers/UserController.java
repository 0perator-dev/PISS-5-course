package com.spring.course.auto.shop.controllers;

import com.spring.course.auto.shop.models.Announcement;
import com.spring.course.auto.shop.services.interfaces.IUserService;
import com.spring.course.auto.shop.types.BadMessage;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "*", maxAge = 3600)
@RequiredArgsConstructor
@RestController
@RequestMapping(value = "/api/user", produces = MediaType.APPLICATION_JSON_VALUE)
public class UserController {

    private final IUserService userService;

    @GetMapping(value = "/{username}")
    public ResponseEntity<?> userExistsByUsername(@PathVariable(value = "username") String username) {
        try {
            boolean usernameExist = userService.existsByUsername(username);
            return ResponseEntity.status(HttpStatus.OK).body(usernameExist);
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

    @GetMapping(value = "/announcements")
    public ResponseEntity<?> getAllAnnouncementsOfLoggedUser() {
        try {
            List<Announcement> announcements = userService.getAllAnnouncementsOfLoggedUser();
            return ResponseEntity.status(HttpStatus.OK).body(announcements);
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
