package com.spring.course.auto.shop.services.interfaces;

import org.springframework.core.io.Resource;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.net.MalformedURLException;
import java.util.Set;

public interface IFileService {
    Set<String> store(MultipartFile[] file) throws IOException;

    Resource getFile(String filePath) throws MalformedURLException;

    void deleteFile(String filePath);
}
