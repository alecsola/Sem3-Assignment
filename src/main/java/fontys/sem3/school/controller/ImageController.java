package fontys.sem3.school.controller;

import fontys.sem3.school.persistence.config.ImageStorageProperties;
import lombok.AllArgsConstructor;
import org.springframework.core.io.FileSystemResource;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.nio.file.Path;
import java.nio.file.Paths;

@RestController
@RequestMapping("/image")
@AllArgsConstructor
@CrossOrigin(origins = "http://localhost:5173")
public class ImageController {

    private ImageStorageProperties imageStorageProperties;

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
}
