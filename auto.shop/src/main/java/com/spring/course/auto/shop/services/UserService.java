package com.spring.course.auto.shop.services;

import com.spring.course.auto.shop.models.User;
import com.spring.course.auto.shop.repositories.IUserRepository;
import com.spring.course.auto.shop.services.interfaces.IUserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserService implements IUserService {

    private final IUserRepository userRepository;

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
}
