package fontys.sem3.school.controller;

import fontys.sem3.school.persistence.config.ImageStorageProperties;
import fontys.sem3.school.persistence.repository.ImageRepository;
import lombok.AllArgsConstructor;
import org.springframework.core.io.FileSystemResource;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Comparator;

import java.io.File;

@RestController
@RequestMapping("/image")
@AllArgsConstructor
@CrossOrigin(origins = "http://localhost:5173")
public class ImageController {

    private ImageStorageProperties imageStorageProperties;
    private ImageRepository repository;

    @GetMapping("/{theatreId}/{imageName}")
    public ResponseEntity<Resource> serveFile(@PathVariable long theatreId, @PathVariable String imageName) {
        String baseDir = imageStorageProperties.getUploadDir();

        Path filePath = Paths.get(baseDir, "TheatreNumber_" + theatreId, imageName);
        Resource resource = new FileSystemResource(filePath);

        if (resource.exists() && resource.isReadable()) {
            return ResponseEntity.ok()
                    .header(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_OCTET_STREAM_VALUE)
                    .header(HttpHeaders.CONTENT_DISPOSITION, "inline; filename=" + imageName)
                    .body(resource);
        } else {
            return ResponseEntity.notFound().build();
        }
    }
    @GetMapping("/event/{eventId}/{imageName}")
    public ResponseEntity<Resource> serveFileEvent(@PathVariable long eventId, @PathVariable String imageName) {
        String baseDir = imageStorageProperties.getUploadDir();

        Path filePath = Paths.get(baseDir, "EventNumber_" + eventId, imageName);
        Resource resource = new FileSystemResource(filePath);

        if (resource.exists() && resource.isReadable()) {
            return ResponseEntity.ok()
                    .header(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_OCTET_STREAM_VALUE)
                    .header(HttpHeaders.CONTENT_DISPOSITION, "inline; filename=" + imageName)
                    .body(resource);
        } else {
            return ResponseEntity.notFound().build();
        }
    }
    @Transactional
    @DeleteMapping("/{eventId}")
    public ResponseEntity<String> deleteImage(@PathVariable long eventId) {
        String baseDir = imageStorageProperties.getUploadDir();
        Path folderPath = Paths.get(baseDir, "EventNumber_" + eventId).toAbsolutePath();
        File folder = folderPath.toFile();

        if (folder.exists() && folder.isDirectory()) {
            try {
                Files.walk(folderPath)
                        .sorted(Comparator.reverseOrder())
                        .map(Path::toFile)
                        .forEach(File::delete);
                repository.deleteByEvent_Id(eventId);
                return ResponseEntity.ok().body("Image folder deleted successfully");
            } catch (IOException e) {
                return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Failed to delete image folder");
            }
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}
