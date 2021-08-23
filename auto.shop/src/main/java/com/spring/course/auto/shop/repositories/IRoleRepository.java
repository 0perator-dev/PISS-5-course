package com.spring.course.auto.shop.repositories;

import com.spring.course.auto.shop.models.Role;
import com.spring.course.auto.shop.models.enums.ERole;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface IRoleRepository extends CrudRepository<Role, Long> {
    Optional<Role> findByName(ERole name);

    Boolean existsByName(ERole name);
}
