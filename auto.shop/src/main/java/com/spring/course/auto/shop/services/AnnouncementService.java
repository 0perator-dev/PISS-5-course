package com.spring.course.auto.shop.services;

import com.spring.course.auto.shop.models.Announcement;
import com.spring.course.auto.shop.models.Image;
import com.spring.course.auto.shop.models.entities.AnnouncementEntity;
import com.spring.course.auto.shop.repositories.IAnnouncementRepository;
import com.spring.course.auto.shop.repositories.IImageRepository;
import com.spring.course.auto.shop.services.interfaces.IFileService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.constraints.NotNull;
import java.io.IOException;
import java.util.HashSet;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Set;


@Service
@RequiredArgsConstructor
public class AnnouncementService {

    private final IAnnouncementRepository announcementRepository;
    private final IImageRepository imageRepository;
    private final MappingManager mappingManager;
    private final IFileService fileService;

    public Page<Announcement> findAll(Pageable pageable) {
        return this.announcementRepository.findAll(pageable);
    }

    public Announcement findById(Long id) throws NoSuchElementException {
        return this.announcementRepository.findById(id)
                .orElseThrow(() -> new NoSuchElementException("Can't find Announcement with id" + id));
    }

    public void post(AnnouncementEntity announcementEntity, MultipartFile[] files) throws IOException {
        Announcement announcement = this.mappingManager.mapToAnnouncement(announcementEntity);
        Set<String> paths = fileService.store(files);
        Announcement savedAnnouncement = this.announcementRepository.save(announcement);

        for (String path : paths) {
            imageRepository.save(Image.builder()
                    .imgPath(path)
                    .announcement(savedAnnouncement)
                    .build());
        }
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
