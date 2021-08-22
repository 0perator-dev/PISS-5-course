package com.spring.course.auto.shop.repositories;

import com.spring.course.auto.shop.models.Announcement;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IAnnouncementRepository extends PagingAndSortingRepository<Announcement, Long> {
}
