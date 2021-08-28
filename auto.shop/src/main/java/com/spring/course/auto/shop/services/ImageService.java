package com.spring.course.auto.shop.services;

import com.spring.course.auto.shop.models.Image;
import com.spring.course.auto.shop.repositories.IImageRepository;
import com.spring.course.auto.shop.services.interfaces.IFileService;
import com.spring.course.auto.shop.services.interfaces.IImageService;
import lombok.RequiredArgsConstructor;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Service;

import java.net.MalformedURLException;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ImageService implements IImageService {

    private final IFileService fileService;
    private final IImageRepository imageRepository;

    @Override
    public Resource getImage(Long id) throws MalformedURLException {
        Optional<Image> image = imageRepository.findById(id);
        return fileService.getFile(image.get().getImgPath());
    }

    @Override
    public void deleteImage(Long id) {
        Optional<Image> image = imageRepository.findById(id);
        fileService.deleteFile(image.get().getImgPath());
    }
}
