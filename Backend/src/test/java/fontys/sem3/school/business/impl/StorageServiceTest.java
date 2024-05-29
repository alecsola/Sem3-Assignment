package fontys.sem3.school.business.impl;

import fontys.sem3.school.persistence.config.ImageStorageProperties;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.io.TempDir;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class StorageServiceTest {
    @TempDir
    Path tempDir;

    @Test
    public void testStore() throws IOException {
        ImageStorageProperties imageStorageProperties = mock(ImageStorageProperties.class);
        StorageService storageService = new StorageService(imageStorageProperties);

        when(imageStorageProperties.getUploadDir()).thenReturn(tempDir.toString());

        MultipartFile file = new MockMultipartFile("file", "original.txt", "text/plain", "Hello World".getBytes());
        storageService.store(file, 1L);

        Path storedFile = tempDir.resolve("TheatreNumber_1").resolve("original.txt");
        assertTrue(Files.exists(storedFile));
    }

    @Test
    public void testStoreEvent() throws IOException {
        ImageStorageProperties imageStorageProperties = mock(ImageStorageProperties.class);
        StorageService storageService = new StorageService(imageStorageProperties);

        when(imageStorageProperties.getUploadDir()).thenReturn(tempDir.toString());

        MultipartFile file = new MockMultipartFile("file", "original.txt", "text/plain", "Hello World".getBytes());
        storageService.storeEvent(file, 1L);

        Path storedFile = tempDir.resolve("EventNumber_1").resolve("original.txt");
        assertTrue(Files.exists(storedFile));
    }
}
