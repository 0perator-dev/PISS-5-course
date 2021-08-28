package com.spring.course.auto.shop.repositories;

import com.spring.course.auto.shop.models.Comment;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ICommentRepository extends PagingAndSortingRepository<Comment, Long> {
    Page<Comment> findAllByAnnouncementId(Pageable pageable, Long id);
}
