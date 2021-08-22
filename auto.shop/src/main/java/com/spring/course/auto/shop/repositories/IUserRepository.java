package com.spring.course.auto.shop.repositories;

import com.spring.course.auto.shop.models.User;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IUserRepository extends CrudRepository<User, Long> {
}
