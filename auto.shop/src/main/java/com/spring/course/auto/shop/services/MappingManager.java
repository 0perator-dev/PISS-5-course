package com.spring.course.auto.shop.services;

import com.spring.course.auto.shop.models.Announcement;
import com.spring.course.auto.shop.models.User;
import com.spring.course.auto.shop.models.entities.AnnouncementEntity;
import com.spring.course.auto.shop.models.entities.ReferenceEntity;
import org.springframework.stereotype.Service;

@Service
public class MappingManager {
    public AnnouncementEntity mapToAnnouncementEntity(Announcement source) {
        AnnouncementEntity announcementEntity = new AnnouncementEntity();

        announcementEntity.setId(source.getId());
        announcementEntity.setDescription(source.getDescription());
        announcementEntity.setTitle(source.getTitle());

        ReferenceEntity owner = new ReferenceEntity(source.getUser().getId(), source.getUser().getName());
        announcementEntity.setOwner(owner);

        return announcementEntity;
    }

    public Announcement mapToAnnouncement(AnnouncementEntity source) {
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

}
