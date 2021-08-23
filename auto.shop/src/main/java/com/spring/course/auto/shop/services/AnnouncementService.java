package com.spring.course.auto.shop.services;

import com.spring.course.auto.shop.models.Announcement;
import com.spring.course.auto.shop.repositories.IAnnouncementRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import javax.validation.constraints.NotNull;
import java.util.NoSuchElementException;


@Service
public class AnnouncementService {
    @Autowired
    private IAnnouncementRepository announcementRepository;

    public Page<Announcement> findAll(Pageable pageable) {
        return this.announcementRepository.findAll(pageable);
    }

    public Announcement findById(Long id) throws NoSuchElementException {
        return this.announcementRepository.findById(id)
                .orElseThrow(() -> new NoSuchElementException("Can't find Announcement with id" + id));
    }

    public void post(Announcement announcement) {
        Announcement savedAnnouncement = this.announcementRepository.save(announcement);
    }

    public void put(@NotNull Announcement announcement) {
        if (!this.announcementRepository.existsById(announcement.getId())) {
            throw new NoSuchElementException("Can't find such announcement for modification");
        }

        this.announcementRepository.save(announcement);
    }

    public void deleteById(Long id) {
        this.announcementRepository.deleteById(id);
    }
}
