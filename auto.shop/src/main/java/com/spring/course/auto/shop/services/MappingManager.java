package com.spring.course.auto.shop.services;

import com.spring.course.auto.shop.models.Announcement;
import com.spring.course.auto.shop.models.Comment;
import com.spring.course.auto.shop.models.User;
import com.spring.course.auto.shop.models.entities.AnnouncementEntity;
import com.spring.course.auto.shop.models.entities.CommentEntity;
import com.spring.course.auto.shop.models.entities.ReferenceEntity;
import org.jetbrains.annotations.NotNull;
import org.springframework.stereotype.Service;


import java.util.ArrayList;
import java.util.List;

@Service
public class MappingManager {
    public AnnouncementEntity mapToAnnouncementEntity(@NotNull Announcement source) {
        AnnouncementEntity announcementEntity = new AnnouncementEntity();

        announcementEntity.setId(source.getId());
        announcementEntity.setDescription(source.getDescription());
        announcementEntity.setTitle(source.getTitle());

        ReferenceEntity owner = new ReferenceEntity(source.getUser().getId(), source.getUser().getName());
        announcementEntity.setOwner(owner);

        return announcementEntity;
    }

    public Announcement mapToAnnouncement(@NotNull AnnouncementEntity source) {
        Announcement announcement = new Announcement();

        User user;

        ReferenceEntity reference = source.getOwner();

        if (reference != null) {
            user = new User();
            user.setId(reference.getId());

            if (reference.getName() != null) {
                user.setName(reference.getName());
            }

            announcement.setUser(user);
        }

        announcement.setId(source.getId());
        announcement.setDescription(source.getDescription());
        announcement.setTitle(source.getTitle());

        return announcement;
    }

    public List<CommentEntity> mapToMessageEntities(@NotNull List<Comment> source) {
        List<CommentEntity> commentEntities = new ArrayList<>();

        source.forEach(comment -> {
            commentEntities.add(this.mapToMessageEntity(comment));
        });

        return commentEntities;
    }

    public CommentEntity mapToMessageEntity(@NotNull Comment source) {
        CommentEntity commentEntity = new CommentEntity();

        commentEntity.setMessage(source.getMessage());
        commentEntity.setId(source.getId());
        commentEntity.setAnnouncementId(source.getAnnouncement().getId());
        commentEntity.setUserId(source.getUser().getId());

        return commentEntity;
    }

    public Comment mapToComment(@NotNull CommentEntity source) {
        Comment comment = new Comment();
        Announcement announcement = new Announcement();
        User user = new User();

        user.setId(source.getUserId());
        announcement.setId(source.getAnnouncementId());

        comment.setMessage(source.getMessage());
        comment.setId(source.getId());
        comment.setAnnouncement(announcement);
        comment.setUser(user);

        return comment;
    }
}
