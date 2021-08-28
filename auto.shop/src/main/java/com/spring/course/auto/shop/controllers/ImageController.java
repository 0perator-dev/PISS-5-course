package com.spring.course.auto.shop.controllers;

import com.spring.course.auto.shop.services.interfaces.IImageService;
import com.spring.course.auto.shop.types.BadMessage;
import lombok.RequiredArgsConstructor;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@CrossOrigin(origins = "*", maxAge = 3600)
@RequiredArgsConstructor
@RestController
@RequestMapping(value = "/api/image", produces = MediaType.APPLICATION_JSON_VALUE)
public class ImageController {

    private final IImageService imageService;

    @GetMapping(value = "/{name}")
    public ResponseEntity<?> getImage(@PathVariable(value = "name") String name) {
        try {
            Resource resource = imageService.getImage(name);
            return ResponseEntity.ok()
                    .header(HttpHeaders.CONTENT_TYPE, MediaType.IMAGE_JPEG_VALUE)
                    .header(HttpHeaders.CONTENT_TYPE, MediaType.IMAGE_PNG_VALUE)
                    .body(resource);
        } catch (Exception exception) {
            return ResponseEntity
                    .status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(new BadMessage(exception.getMessage()));
        }
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity<?> deleteImage(@PathVariable(value = "id") Long id) {
        try {
            imageService.deleteImage(id);
            return ResponseEntity.ok().body(null);
        } catch (Exception exception) {
            return ResponseEntity
                    .status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(new BadMessage(exception.getMessage()));
        }
    }
}
