package com.example.ez.services.service;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import java.io.IOException;
import java.nio.file.*;
import java.util.UUID;

@Service
public class ImageUploadService {

    @Value("${cms.upload.dir:uploads}")
    private String uploadDir;

    @Value("${cms.upload.base-url:http://localhost:8080/uploads}")
    private String baseUrl;

    public String upload(MultipartFile file) throws IOException {
        Path dir = Paths.get(uploadDir);
        if (!Files.exists(dir)) Files.createDirectories(dir);

        String ext = "";
        String original = file.getOriginalFilename();
        if (original != null && original.contains("."))
            ext = original.substring(original.lastIndexOf('.'));

        String filename = UUID.randomUUID() + ext;
        Files.copy(file.getInputStream(), dir.resolve(filename),
            StandardCopyOption.REPLACE_EXISTING);

        return baseUrl + "/" + filename;
    }
}