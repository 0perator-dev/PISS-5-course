package com.spring.course.auto.shop.services.interfaces;

import org.springframework.core.io.Resource;

import java.net.MalformedURLException;

public interface IImageService {
    Resource getImage(Long id) throws MalformedURLException;

    void deleteImage(Long id);
}
