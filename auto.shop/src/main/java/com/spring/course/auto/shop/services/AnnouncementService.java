package com.spring.course.auto.shop.services;

import com.google.gson.Gson;
import com.nimbusds.jose.shaded.json.parser.ParseException;
import com.spring.course.auto.shop.models.Announcement;
import com.spring.course.auto.shop.models.GoogleItemEntity;
import com.spring.course.auto.shop.models.GoogleResponseObj;
import com.spring.course.auto.shop.models.Image;
import com.spring.course.auto.shop.models.entities.AnnouncementEntity;
import com.spring.course.auto.shop.repositories.IAnnouncementRepository;
import com.spring.course.auto.shop.repositories.IImageRepository;
import com.spring.course.auto.shop.services.interfaces.IFileService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.multipart.MultipartFile;

import javax.imageio.ImageIO;
import javax.validation.constraints.NotNull;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.*;


@Service
@RequiredArgsConstructor
public class AnnouncementService {

    @Value("${api.key}")
    private String apiKey;

    @Value("${programmable.search.engine.id}")
    private String engineId;

    private String URI_TEMPLATE = "https://www.googleapis.com/customsearch/v1?key=%s&cx=%s&q=%s&searchType=image";

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

    public void post(@NotNull AnnouncementEntity announcementEntity, MultipartFile[] files) throws IOException, ParseException {
        Announcement announcement = this.mappingManager.mapToAnnouncement(announcementEntity);

        if (files != null && files.length > 0 && files[0].isEmpty()) {
            String uri = String.format(URI_TEMPLATE, this.apiKey, this.engineId, announcement.getTitle());
            RestTemplate restTemplate = new RestTemplate();
            String googleSearchResponse = restTemplate.getForObject(uri, String.class);
            Gson g = new Gson();
            GoogleResponseObj googleResponseObj = g.fromJson(googleSearchResponse, GoogleResponseObj.class);
            String imageLink = googleResponseObj.getItems().get(0).getLink();

            BufferedImage originalImage = ImageIO.read(new URL(imageLink));
            ByteArrayOutputStream baos = new ByteArrayOutputStream();
            ImageIO.write( originalImage, "jpg", baos );
            baos.flush();

            MultipartFile multipartFile = new MockMultipartFile("fileName", ".JPG",
                    "image/jpg", baos.toByteArray());
            files[0] = multipartFile;
        }

        Set<String> paths = fileService.store(files);
        Announcement savedAnnouncement = this.announcementRepository.save(announcement);

        for (String path : paths) {
            imageRepository.save(Image.builder()
                    .imgPath(path)
                    .announcement(savedAnnouncement)
                    .build());
        }
    }

    public void put(@NotNull Long id, @NotNull Announcement announcement) {
        if (!this.announcementRepository.existsById(announcement.getId())) {
            throw new NoSuchElementException("Can't find such announcement for modification");
        }

        Announcement repoAnnouncement = this.announcementRepository.findById(id).get();

        repoAnnouncement.setDescription(announcement.getDescription());
        repoAnnouncement.setTitle(announcement.getTitle());

        this.announcementRepository.save(repoAnnouncement);
    }

    public void deleteById(Long id) {
        this.announcementRepository.deleteById(id);
    }
}
