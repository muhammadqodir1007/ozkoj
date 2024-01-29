package com.alibou.security.image;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.UUID;

@Service
public class ImageService {

    @Value("${upload.path}") // Configure the upload path in application.properties
    private String uploadPath;

    public String saveImage(MultipartFile file) throws IOException {
        // Generate a unique filename or use the original filename
        String filename = UUID.randomUUID() + "_" + file.getOriginalFilename();
        String filePath = Paths.get(uploadPath, filename).toString();

        // Save the file to the server
        Files.createDirectories(Paths.get(uploadPath)); // Create directories if not exist
        Files.copy(file.getInputStream(), Paths.get(filePath), StandardCopyOption.REPLACE_EXISTING);

        // Return the file path or URL
        return "static/images/" + filename; // This is just an example, modify as needed
    }

    public byte[] readImage(String filename) throws IOException {
        Path imagePath = Path.of(uploadPath, filename);
        return Files.readAllBytes(imagePath);
    }

}
