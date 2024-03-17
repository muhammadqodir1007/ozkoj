// ImageService.java
package uz.fazo.image;

import org.apache.tika.Tika;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
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

    @Value("${upload.path}")
    private String uploadPath;

    public String saveImage(MultipartFile file) throws IOException {
        String filename = generateUniqueFilename(file);
        String filePath = Paths.get(uploadPath, filename).toString();
        Files.createDirectories(Paths.get(uploadPath));
        Files.copy(file.getInputStream(), Paths.get(filePath), StandardCopyOption.REPLACE_EXISTING);
        return filename;
    }

    public byte[] readImage(String filename) throws IOException {
        Path imagePath = Path.of(uploadPath, filename);
        return Files.readAllBytes(imagePath);
    }

    public ResponseEntity<Resource> getImageResource(String filename) throws IOException {
        byte[] fileBytes = readImage(filename);
        String contentType = determineContentType(filename);

        ByteArrayResource resource = new ByteArrayResource(fileBytes);
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.parseMediaType(contentType));

        return ResponseEntity.ok()
                .headers(headers)
                .contentLength(fileBytes.length)
                .body(resource);
    }

    public ResponseEntity<Resource> downloadImage(String filename) throws IOException {
        Path filePath = Path.of(uploadPath, filename);
        Resource resource = new ByteArrayResource(Files.readAllBytes(filePath));

        if (Files.exists(filePath) && Files.isReadable(filePath)) {
            HttpHeaders headers = new HttpHeaders();
            headers.add(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=" + filename);
            return ResponseEntity.ok()
                    .headers(headers)
                    .contentLength(Files.size(filePath))
                    .body(resource);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    public void deleteImage(String filename) throws IOException {
        Path imagePath = Path.of(uploadPath, filename);
        Files.deleteIfExists(imagePath);
    }

    private String generateUniqueFilename(MultipartFile file) {
        return UUID.randomUUID() + "_" + file.getOriginalFilename();
    }

    private String determineContentType(String filename) {
        Tika tika = new Tika();
        return tika.detect(filename);
    }
}
