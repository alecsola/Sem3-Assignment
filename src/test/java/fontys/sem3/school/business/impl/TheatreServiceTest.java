package fontys.sem3.school.business.impl;

import fontys.sem3.school.business.Request.Theatre.TheatreRequest;
import fontys.sem3.school.business.servicesInterfaces.ITheatreService;
import fontys.sem3.school.domain.Theatre;
import fontys.sem3.school.persistence.repository.TheatreRepository;
import jakarta.transaction.Transactional;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.web.multipart.MultipartFile;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.mock;


public class TheatreServiceTest {




    @Mock
    private StorageService storageService;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
        // Additional setup if needed
    }


    /**
     * @verifies creatre theatre
     * @see TheatreService#createTheatre(fontys.sem3.school.business.Request.Theatre.TheatreRequest)
     */
    private List<MultipartFile> createSampleMultipartFile() {
        List <MultipartFile> files = new ArrayList<>();
        files.add (new MockMultipartFile("file", "sample-image.jpg", "image/jpeg", "Sample Image Content".getBytes()));
        return files;
    }
    @Transactional
    @Test
    public void createTheatre_shouldCreatreTheatre() throws Exception {
        TheatreRepository theatreRepository = mock(TheatreRepository.class);
        StorageService storage = mock(StorageService.class);
        List<MultipartFile>images = createSampleMultipartFile();

        TheatreRequest request = new TheatreRequest("Name", images, "C1","C1","D1",1,2);
        Mockito.when(theatreRepository.createTheatre(Mockito.any(Theatre.class))).thenReturn(1L);
        ITheatreService sut = new TheatreService(theatreRepository, storage);
        // Act
        long result = sut.createTheatre(request);

        // Assert
        assertEquals(1L, result);
        Mockito.verify(storage, Mockito.times(1)).store(Mockito.any(MultipartFile.class), Mockito.eq(1L));
    }

    /**
     * @verifies return an error when not creating
     * @see TheatreService#createTheatre(fontys.sem3.school.business.Request.Theatre.TheatreRequest)
     */
    @Test
    public void createTheatre_shouldReturnAnErrorWhenNotCreating() throws Exception {
        TheatreRepository theatreRepository = mock(TheatreRepository.class);
        StorageService storage = mock(StorageService.class);
        List<MultipartFile>images = createSampleMultipartFile();

        TheatreRequest request = new TheatreRequest("Name", images, "C1","C1","D1",1,2);
        Mockito.when(theatreRepository.createTheatre(Mockito.any(Theatre.class))).thenReturn(1L);
        ITheatreService sut = new TheatreService(theatreRepository, storage);
        // Act
        long result = sut.createTheatre(request);

        // Assert
        assertEquals(1L, result);
        Mockito.verify(storage, Mockito.times(1)).store(Mockito.any(MultipartFile.class), Mockito.eq(1L));
    }

    /**
     * @verifies filter theatre with name,city and country
     * @see TheatreService#filterTheatres(String, String, String)
     */
    @Test
    public void filterTheatres_shouldFilterTheatreWithNamecityAndCountry() throws Exception {

    }

    /**
     * @verifies return an empty list when nothing found
     * @see TheatreService#filterTheatres(String, String, String)
     */
    @Test
    public void filterTheatres_shouldReturnAnEmptyListWhenNothingFound() throws Exception {
        //TODO auto-generated
        Assertions.fail("Not yet implemented");
    }
}
