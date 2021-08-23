package com.spring.course.auto.shop.services;

import com.spring.course.auto.shop.models.Role;
import com.spring.course.auto.shop.models.User;
import com.spring.course.auto.shop.models.dtos.requests.UserToLogin;
import com.spring.course.auto.shop.models.dtos.requests.UserToRegister;
import com.spring.course.auto.shop.models.dtos.responces.AuthenticatedUser;
import com.spring.course.auto.shop.models.dtos.responces.LoggedUser;
import com.spring.course.auto.shop.models.enums.ERole;
import com.spring.course.auto.shop.repositories.IRoleRepository;
import com.spring.course.auto.shop.security.jwt.JwtUtils;
import com.spring.course.auto.shop.security.models.AuthenticatedUserPrincipals;
import com.spring.course.auto.shop.services.interfaces.IAuthService;
import com.spring.course.auto.shop.services.interfaces.IUserService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class AuthService implements IAuthService {

    private final IUserService userService;
    private final IRoleRepository roleRepository;
    private final PasswordEncoder encoder;
    private final AuthenticationManager authenticationManager;
    private final JwtUtils jwtUtils;

    @Override
    public AuthenticatedUser register(UserToRegister userToRegister) {

        if (userService.existsByUsername(userToRegister.getUsername())) {
            throw new IllegalArgumentException("Username is already taken!");
        }

        Set<Role> roles = new HashSet<>();
        userToRegister.getRoles().forEach(role -> {
            switch (role) {
                case ROLE_USER:
                    Role adminRole = roleRepository.findByName(ERole.ROLE_USER)
                            .orElseThrow(() -> new RuntimeException("Error: Role is not found."));
                    roles.add(adminRole);
                    break;
                case ROLE_ADMINISTRATOR:
                    Role modRole = roleRepository.findByName(ERole.ROLE_ADMINISTRATOR)
                            .orElseThrow(() -> new RuntimeException("Error: Role is not found."));
                    roles.add(modRole);
                    break;
                default:
                    throw new RuntimeException("Error: Role is not found.");
            }
        });

        User user = User.builder()
                .username(userToRegister.getUsername())
                .password(encoder.encode(userToRegister.getPassword()))
                .name(userToRegister.getName())
                .roles(roles)
                .build();

        User addedUser = userService.addUser(user);

        return new AuthenticatedUser(addedUser.getId(), addedUser.getUsername(), null);
    }

    @Override
    public LoggedUser login(UserToLogin userToLogin) {

        User customer = userService.findByUsername(userToLogin.getUsername())
                .orElseThrow(() -> new IllegalArgumentException("User entered an invalid username."));

        if (!encoder.matches(userToLogin.getPassword(), customer.getPassword())) {
            throw new IllegalArgumentException("User entered an invalid password.");
        }

        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(userToLogin.getUsername(), userToLogin.getPassword()));

        SecurityContextHolder.getContext().setAuthentication(authentication);
        String jwt = jwtUtils.generateJwtToken(authentication);

        AuthenticatedUserPrincipals userDetails = (AuthenticatedUserPrincipals) authentication.getPrincipal();
        Set<String> roles = userDetails.getAuthorities().stream()
                .map(GrantedAuthority::getAuthority)
                .collect(Collectors.toSet());

        return new LoggedUser(jwt, new AuthenticatedUser(userDetails.getId(), userDetails.getUsername(), roles));
    }
}
