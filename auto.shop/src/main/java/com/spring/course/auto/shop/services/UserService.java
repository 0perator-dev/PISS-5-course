package com.spring.course.auto.shop.services;

import com.spring.course.auto.shop.models.Announcement;
import com.spring.course.auto.shop.models.Image;
import com.spring.course.auto.shop.models.User;
import com.spring.course.auto.shop.models.dtos.requests.UserToUpdate;
import com.spring.course.auto.shop.models.dtos.responces.UserDetails;
import com.spring.course.auto.shop.repositories.IAnnouncementRepository;
import com.spring.course.auto.shop.repositories.IImageRepository;
import com.spring.course.auto.shop.repositories.IUserRepository;
import com.spring.course.auto.shop.security.models.AuthenticatedUserPrincipals;
import com.spring.course.auto.shop.services.interfaces.IUserService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class UserService implements IUserService {

    @Value("${image.url}")
    private String imageUrl;

    private final IUserRepository userRepository;
    private final IAnnouncementRepository announcementRepository;
    private final IImageRepository imageRepository;

    @Override
    public boolean existsByUsername(String username) {
        return userRepository.existsByUsername(username);
    }

    @Override
    public User addUser(User user) {
        return userRepository.save(user);
    }

    @Override
    public Optional<User> findByUsername(String username) {
        return userRepository.findByUsername(username);
    }

    @Override
    public List<Announcement> getAllAnnouncementsOfLoggedUser() {
        AuthenticatedUserPrincipals loggedUserDetails = (AuthenticatedUserPrincipals) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        List<Announcement> announcements = announcementRepository.findByUserId(loggedUserDetails.getId());
        for (Announcement announcement : announcements) {
            List<Image> images = imageRepository.findByAnnouncementId(announcement.getId());
            images.forEach(image -> image.setImgPath(imageUrl + image.getImgPath()));
            announcement.setImages(new HashSet<>(images));
        }
        return announcements;
    }

    @Override
    public UserDetails getUserDetails(Long id) {
        User user = userRepository.findById(id).get();
        return new UserDetails(user.getUsername(), user.getName(), user.getRoles());
    }

    @Override
    public Iterable<User> getAllUsers() {
        return userRepository.findAll();
    }

    @Override
    public void updateUserDetails(Long id, UserToUpdate user) {
        if (!userRepository.existsById(id)) {
            throw new NoSuchElementException("Can't find such гыук for modification");
        }
        User userToUpdate = userRepository.findById(id).get();
        userToUpdate.setUsername(user.getUsername());
        userToUpdate.setPassword(user.getPassword());
        userToUpdate.setName(user.getName());
        userRepository.save(userToUpdate);
    }

    @Override
    public void deleteUser(Long id) {
        Optional<User> user = userRepository.findById(id);
        if (!user.isPresent()) {
            throw new NoSuchElementException("There is no such user to delete.");
        }
        userRepository.delete(user.get());
    }
}
