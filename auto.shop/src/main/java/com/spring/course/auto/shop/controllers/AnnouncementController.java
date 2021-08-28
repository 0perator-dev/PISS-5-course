package com.spring.course.auto.shop.controllers;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.spring.course.auto.shop.models.Announcement;
import com.spring.course.auto.shop.models.entities.AnnouncementEntity;
import com.spring.course.auto.shop.services.AnnouncementService;
import com.spring.course.auto.shop.services.MappingManager;
import com.spring.course.auto.shop.types.BadMessage;
import com.spring.course.auto.shop.types.QueryResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;

@RestController
@RequiredArgsConstructor
@RequestMapping(path = "/api/announcements", produces = MediaType.APPLICATION_JSON_VALUE)
public class AnnouncementController {
    private final AnnouncementService announcementService;
    private final MappingManager mappingManager;

    @GetMapping()
    public ResponseEntity<QueryResponse<AnnouncementEntity>> query(
            @RequestParam(name = "page", required = false, defaultValue = "1") int page,
            @RequestParam(name = "pageSize", required = false, defaultValue = "10") int pageSize
    ) {
        page--;
        Page<Announcement> announcementsPage = this.announcementService.findAll(PageRequest.of(page, pageSize));
        page++;

        List<AnnouncementEntity> entityList = new ArrayList<>();

        announcementsPage.getContent().forEach(announcement -> {
            entityList.add(this.mappingManager.mapToAnnouncementEntity(announcement));
        });

        QueryResponse<AnnouncementEntity> queryResponse = new QueryResponse<>();
        queryResponse.setPage(page);
        queryResponse.setPageSize(pageSize);
        queryResponse.setTotalPages(announcementsPage.getTotalPages());
        queryResponse.setValues(entityList);

        return ResponseEntity.ok().body(queryResponse);
    }

    @GetMapping("{id}")
    public ResponseEntity<?> get(@PathVariable Long id) {
        Announcement announcement;

        try {
            announcement = this.announcementService.findById(id);
        } catch (NoSuchElementException exception) {
            return ResponseEntity.badRequest().body(new BadMessage(exception.getMessage()));
        }

        return ResponseEntity.ok().body(this.mappingManager.mapToAnnouncementEntity(announcement));
    }

    @PostMapping
    public ResponseEntity<?> post(@RequestParam("object") String announcementStr, @RequestParam("files") MultipartFile[] files) throws JsonProcessingException {
        try {
            AnnouncementEntity announcementEntity = new ObjectMapper().readValue(announcementStr, AnnouncementEntity.class);
            this.announcementService.post(announcementEntity, files);
        } catch (Exception exception) {
            return ResponseEntity.badRequest().body(new BadMessage(exception.getMessage()));
        }

        return ResponseEntity.ok().body(null);
    }

    @PutMapping("{id}")
    public ResponseEntity<?> put(@Valid @NotNull @RequestBody AnnouncementEntity entity) {
        Announcement announcement = this.mappingManager.mapToAnnouncement(entity);

        try {
            this.announcementService.put(announcement);
        } catch (Exception exception) {
            return ResponseEntity.badRequest().body(new BadMessage(exception.getMessage()));
        }

        return ResponseEntity.ok().body(null);
    }

    @DeleteMapping("{id}")
    public ResponseEntity<?> deleteAnnouncement(@PathVariable Long id) {
        try {
            this.announcementService.deleteById(id);
        } catch (EmptyResultDataAccessException exception) {
            return ResponseEntity.badRequest().body(new BadMessage(exception.getMessage()));
        }

        return ResponseEntity.ok().body(null);
    }
}
