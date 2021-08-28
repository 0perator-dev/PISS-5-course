package com.spring.course.auto.shop.services;

import com.spring.course.auto.shop.services.interfaces.IFileService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.*;

@Service
public class FileService implements IFileService {

    @Value("${upload.path}")
    private Path uploadFolder;

    @Value("${get.user.project.dir}")
    private String userProjectDir;

    @Override
    public Set<String> store(MultipartFile[] imageFiles) throws IOException {
        Set<String> uniqueImageNames = new HashSet<>();
        for (MultipartFile imageFile : imageFiles) {
            if (!imageFile.isEmpty()) {
                Path uploadPath = Paths.get(System.getProperty(userProjectDir) + this.uploadFolder.toString());

                File directory = new File(uploadPath.toString());
                if (!directory.exists()) {
                    directory.mkdirs();
                }

                String uniqueName = UUID.randomUUID().toString() + imageFile.getOriginalFilename();
                Files.copy(imageFile.getInputStream(), uploadPath.resolve(uniqueName));
                uniqueImageNames.add(uniqueName);
            }
        }
        return uniqueImageNames;
    }

    @Override
    public Resource getFile(String filePath) throws MalformedURLException {
        Path uploadPath = Paths.get(System.getProperty(userProjectDir) + this.uploadFolder.toString());
        File file = uploadPath.resolve(filePath).toFile();
        if (file.exists() && !file.isDirectory()) {
            return new UrlResource(file.toURI());
        } else
            throw new IllegalArgumentException("File with this name does not exist.");
    }

    @Override
    public void deleteFile(String filePath) {
        try {
            Path uploadPath = Paths.get(System.getProperty(userProjectDir) + this.uploadFolder.toString());
            File file = uploadPath.resolve(filePath).toFile();
            if (file.exists() && !file.isDirectory()) {
                file.delete();
            }
        } catch (SecurityException exception) {
            throw exception;
        } catch (Exception exception) {
            throw new IllegalArgumentException("File with this name does not exist.");
        }
    }
}
