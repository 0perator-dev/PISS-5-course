package com.spring.course.auto.shop.repositories;

import com.spring.course.auto.shop.models.Announcement;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IAnnouncementRepository extends CrudRepository<Announcement, Long> {
}
