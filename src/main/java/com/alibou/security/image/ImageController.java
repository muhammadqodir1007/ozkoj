package com.alibou.security.image;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@RestController
@RequestMapping("/api/images")
public class ImageController {

    @Autowired
    private ImageService imageService;

    @PostMapping("/upload")
    public ResponseEntity<String> handleFileUpload(@RequestParam("file") MultipartFile file) {
        try {
            // Save the file to the server or cloud storage
            String imageUrl = imageService.saveImage(file);

            // Save the image URL in the database or return it to the client
            // (You should handle database storage or client response based on your requirements)

            return ResponseEntity.ok("Image uploaded successfully. Image URL: " + imageUrl);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Failed to upload image");
        }
    }


    @GetMapping("/{filename}")
    public ResponseEntity<Resource> getImage(@PathVariable String filename) throws IOException {
        byte[] imageBytes = imageService.readImage(filename);

        // Create a ByteArrayResource and set the content type
        ByteArrayResource resource = new ByteArrayResource(imageBytes);
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.IMAGE_JPEG); // Set the appropriate media type

        // Return a ResponseEntity with the resource and headers
        return ResponseEntity.ok()
                .headers(headers)
                .contentLength(imageBytes.length)
                .body(resource);
    }
}
