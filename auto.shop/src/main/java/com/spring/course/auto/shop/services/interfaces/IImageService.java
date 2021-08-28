package com.spring.course.auto.shop.services.interfaces;

import org.springframework.core.io.Resource;

import java.net.MalformedURLException;

public interface IImageService {
    Resource getImage(String name) throws MalformedURLException;

    void deleteImage(Long id);
}
