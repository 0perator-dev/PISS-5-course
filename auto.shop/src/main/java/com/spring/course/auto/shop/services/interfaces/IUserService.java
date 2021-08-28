package com.spring.course.auto.shop.services.interfaces;

import com.spring.course.auto.shop.models.Announcement;
import com.spring.course.auto.shop.models.User;

import java.util.List;
import java.util.Optional;

public interface IUserService {
    boolean existsByUsername(String username);

    User addUser(User user);

    Optional<User> findByUsername(String username);

    List<Announcement> getAllAnnouncementsOfLoggedUser();

    void deleteUser(Long id);
}
