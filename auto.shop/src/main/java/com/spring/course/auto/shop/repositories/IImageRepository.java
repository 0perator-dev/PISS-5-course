package com.spring.course.auto.shop.repositories;

import com.spring.course.auto.shop.models.Image;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface IImageRepository extends CrudRepository<Image, Long> {
    List<Image> findByAnnouncementId(Long announcementId);
}
