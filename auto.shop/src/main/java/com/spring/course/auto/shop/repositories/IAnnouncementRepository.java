package com.spring.course.auto.shop.repositories;

import com.spring.course.auto.shop.models.Announcement;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface IAnnouncementRepository extends PagingAndSortingRepository<Announcement, Long> {
    List<Announcement> findByUserId(Long id);
}
