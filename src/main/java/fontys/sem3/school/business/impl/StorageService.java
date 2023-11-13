package fontys.sem3.school.business.impl;

import fontys.sem3.school.persistence.ImageStorageProperties;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Objects;

@Service
public class StorageService {

    private final ImageStorageProperties imageStorageProperties;
    @Autowired
    public StorageService(ImageStorageProperties imageStorageProperties){
        this.imageStorageProperties= imageStorageProperties;
    }

    public void store(MultipartFile file, Long theatreId) {
        try {
            if (file.isEmpty()) {
                throw new RuntimeException("Failed to store empty file " + file.getOriginalFilename());
            }

            String baseDir = imageStorageProperties.getUploadDir();
            System.out.println("Base Directory: " + baseDir);  // Add this line

            Path productFolder = Paths.get(baseDir, "TheatreNumber_" + theatreId);
            System.out.println("Product Folder: " + productFolder);  // Add this line

            if (!Files.exists(productFolder)) {
                Files.createDirectories(productFolder);
            }

            Files.copy(file.getInputStream(), productFolder.resolve(Objects.requireNonNull(file.getOriginalFilename())));
        } catch (IOException e) {
            throw new RuntimeException("Failed to store file " + file.getOriginalFilename(), e);
        }
    }
}
