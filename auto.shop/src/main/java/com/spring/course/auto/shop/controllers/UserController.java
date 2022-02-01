package com.spring.course.auto.shop.controllers;

import com.spring.course.auto.shop.models.Announcement;
import com.spring.course.auto.shop.models.User;
import com.spring.course.auto.shop.models.dtos.requests.UserToUpdate;
import com.spring.course.auto.shop.models.dtos.responces.UserDetails;
import com.spring.course.auto.shop.models.entities.AnnouncementEntity;
import com.spring.course.auto.shop.services.interfaces.IUserService;
import com.spring.course.auto.shop.types.BadMessage;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.util.List;

@CrossOrigin(origins = "*", maxAge = 3600)
@RequiredArgsConstructor
@RestController
@RequestMapping(value = "/api/user", produces = MediaType.APPLICATION_JSON_VALUE)
public class UserController {

    private final IUserService userService;

    @GetMapping
    @PreAuthorize("hasRole('ROLE_ADMINISTRATOR')")
    public ResponseEntity<?> getAllUsers() {
        try {
            Iterable<User> users = userService.getAllUsers();
            return ResponseEntity.status(HttpStatus.OK).body(users);
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

    @GetMapping(value = "/{id}/info")
    public ResponseEntity<?> getLoggedUserDetails(@PathVariable(value = "id") Long id) {
        try {
            UserDetails userDetails = userService.getUserDetails(id);
            return ResponseEntity.status(HttpStatus.OK).body(userDetails);
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

    @PutMapping("{id}")
    @PreAuthorize("hasRole('ROLE_ADMINISTRATOR')")
    public ResponseEntity<?> put(@PathVariable(value = "id") String id, @Valid @NotNull @RequestBody UserToUpdate user) {
        try {
            this.userService.updateUserDetails(Long.parseLong(id), user);
        } catch (Exception exception) {
            return ResponseEntity.badRequest().body(new BadMessage(exception.getMessage()));
        }

        return ResponseEntity.ok().body(null);
    }

    @DeleteMapping(value = "/{id}")
    @PreAuthorize("hasRole('ROLE_ADMINISTRATOR')")
    public ResponseEntity<?> deleteUser(@PathVariable(value = "id") Long id) {
        try {
            userService.deleteUser(id);
            return ResponseEntity.status(HttpStatus.OK).body(null);
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
