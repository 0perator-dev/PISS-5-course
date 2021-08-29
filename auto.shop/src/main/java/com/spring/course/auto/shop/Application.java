package com.spring.course.auto.shop;

import com.spring.course.auto.shop.models.Role;
import com.spring.course.auto.shop.models.enums.ERole;
import com.spring.course.auto.shop.repositories.IRoleRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.stereotype.Component;

import java.util.Arrays;

@SpringBootApplication
public class Application extends SpringBootServletInitializer {

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }

}

@Component
@RequiredArgsConstructor
class CommandLineRunnerImpl implements CommandLineRunner {

    private final IRoleRepository roleRepository;

    @Override
    public void run(String... args) throws Exception {

        Role userRole = Role.builder().name(ERole.ROLE_USER).build();
        Role adminRole = Role.builder().name(ERole.ROLE_ADMINISTRATOR).build();

        if (!roleRepository.existsByName(ERole.ROLE_USER) && !roleRepository.existsByName(ERole.ROLE_ADMINISTRATOR)) {
            roleRepository.saveAll(Arrays.asList(userRole, adminRole));
        }
    }
}
