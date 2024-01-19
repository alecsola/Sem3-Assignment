package fontys.sem3.school.business.impl;

import fontys.sem3.school.business.Request.Theatre.TheatreRequest;
import fontys.sem3.school.business.Request.Theatre.UpdateTheatreRequest;
import fontys.sem3.school.business.Response.Theatre.GetTheatreResponse;
import fontys.sem3.school.business.Response.UpdateEventResponse;
import fontys.sem3.school.business.servicesInterfaces.IEventService;
import fontys.sem3.school.business.servicesInterfaces.ITheatreService;
import fontys.sem3.school.domain.Event;
import fontys.sem3.school.domain.Theatre;
import fontys.sem3.school.persistence.repository.EventRepository;
import fontys.sem3.school.persistence.repository.TheatreRepository;
import jakarta.transaction.Transactional;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.web.multipart.MultipartFile;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;
import static org.mockito.Mockito.times;


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
    @Test
    public void testGetEventById() {
        TheatreRepository repository = mock(TheatreRepository.class);
        StorageService storage = mock(StorageService.class);
        long id = 1L;
        Theatre theatre = new Theatre();
        when(repository.getTheatrebyId(id)).thenReturn(theatre);
        ITheatreService sut = new TheatreService(repository, storage);

        Theatre theatre1 = sut.getTheatrebyId(id);

        verify(repository, times(1)).getTheatrebyId(id);
        assertNotNull(theatre1);
    }
    @Test
    public void filterTheatres_shouldFilterTheatresCorrectly() {
        TheatreRepository theatreRepository = mock(TheatreRepository.class);
        StorageService storage = mock(StorageService.class);

        List<Theatre> theatres = new ArrayList<>();
        // Add some sample theatres to the list
        // ...

        when(theatreRepository.filterTheatres("name", "city", "country")).thenReturn(theatres);

        ITheatreService sut = new TheatreService(theatreRepository, storage);

        GetTheatreResponse response = sut.filterTheatres("name", "city", "country");

        assertEquals(theatres, response.getTheatres());
        verify(theatreRepository, times(1)).filterTheatres("name", "city", "country");
    }
    @Test
    public void findAll_shouldReturnAllTheatres() {
        TheatreRepository theatreRepository = mock(TheatreRepository.class);
        StorageService storage = mock(StorageService.class);

        List<Theatre> theatres = new ArrayList<>();

        when(theatreRepository.findAll()).thenReturn(theatres);

        ITheatreService sut = new TheatreService(theatreRepository, storage);

        GetTheatreResponse response = sut.findAll();

        assertEquals(theatres, response.getTheatres());
        verify(theatreRepository, times(1)).findAll();
    }
    @Test
    public void createTheatre_shouldThrowExceptionWhenCreating() throws Exception {
        TheatreRepository theatreRepository = mock(TheatreRepository.class);
        StorageService storage = mock(StorageService.class);
        List<MultipartFile> images = createSampleMultipartFile();

        TheatreRequest request = new TheatreRequest("Name", images, "C1","C1","D1",1,2);

        // Simulate an exception being thrown when createTheatre is called
        doThrow(new RuntimeException("Simulated exception")).when(theatreRepository).createTheatre(any(Theatre.class));

        ITheatreService sut = new TheatreService(theatreRepository, storage);

        // Assert that an IllegalArgumentException is thrown
        assertThrows(IllegalArgumentException.class, () -> {
            sut.createTheatre(request);
        });
    }




}
