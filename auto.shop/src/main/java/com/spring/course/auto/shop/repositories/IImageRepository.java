package com.spring.course.auto.shop.repositories;

import com.spring.course.auto.shop.models.Image;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface IImageRepository extends CrudRepository<Image, Long> {
    List<Image> findByAnnouncementId(Long announcementId);

    Optional<Image> findByImgPath(String imgPath);
}
