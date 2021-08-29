package com.spring.course.auto.shop.services.interfaces;

import com.spring.course.auto.shop.models.Announcement;
import com.spring.course.auto.shop.models.User;
import com.spring.course.auto.shop.models.dtos.requests.UserToUpdate;
import com.spring.course.auto.shop.models.dtos.responces.UserDetails;

import java.util.List;
import java.util.Optional;

public interface IUserService {
    boolean existsByUsername(String username);

    User addUser(User user);

    Optional<User> findByUsername(String username);

    List<Announcement> getAllAnnouncementsOfLoggedUser();

    UserDetails getUserDetails(Long id);

    Iterable<User> getAllUsers();

    void updateUserDetails(Long id, UserToUpdate user);

    void deleteUser(Long id);
}
