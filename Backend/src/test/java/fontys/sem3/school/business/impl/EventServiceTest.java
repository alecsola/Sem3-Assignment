package fontys.sem3.school.business.impl;

import fontys.sem3.school.business.Converter.EventConverterBusiness;
import fontys.sem3.school.business.Request.Event.EventRequest;
import fontys.sem3.school.business.Request.Event.UpdateEventRequest;
import fontys.sem3.school.business.Response.GetEventResponse;
import fontys.sem3.school.business.Response.UpdateEventResponse;
import fontys.sem3.school.business.interfaces.IEventRepositoryBusiness;
import fontys.sem3.school.business.servicesInterfaces.IEventService;
import fontys.sem3.school.business.servicesInterfaces.ITheatreService;
import fontys.sem3.school.domain.Event;
import fontys.sem3.school.domain.Theatre;
import fontys.sem3.school.domain.Zone;
import fontys.sem3.school.persistence.IEventRepository;
import fontys.sem3.school.persistence.repository.EventRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.verification.VerificationMode;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.web.multipart.MultipartFile;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.*;

public class EventServiceTest {
    @InjectMocks
    private EventService eventService;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }
    private List<MultipartFile> createSampleMultipartFile() {
        List <MultipartFile> files = new ArrayList<>();
        files.add (new MockMultipartFile("file", "sample-image.jpg", "image/jpeg", "Sample Image Content".getBytes()));
        return files;
    }
    @Test
    public void testCreateEvent() {
        EventRepository repository = mock(EventRepository.class);
        StorageService storage = mock(StorageService.class);
        Zone zone1 = Zone.builder().id(1L).price(100).availableSeats(50).build();

        SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd");
        Date date = null;
        try {
            date = sdf.parse("2024/04/06");
        } catch (ParseException e) {
            e.printStackTrace();
        }
        List<Zone> zones = Arrays.asList(zone1);
        List<MultipartFile> images = createSampleMultipartFile();
        EventRequest request = new EventRequest("T1",8L,date,"15:05",zones,images);


        when(repository.createEvent(Mockito.any(Event.class))).thenReturn(1L);
        IEventService sut = new EventService(repository, storage);

        long result = sut.createEvent(request);

        Mockito.verify(storage, Mockito.times(1)).storeEvent(Mockito.any(MultipartFile.class), Mockito.eq(1L));
        assertEquals(1L, result);
    }




    @Test
    public void testUpdateEvent() {
        EventRepository repository = mock(EventRepository.class);
        StorageService storage = mock(StorageService.class);
        Zone zone1 = Zone.builder().id(1L).price(100).availableSeats(50).build();

        SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd");
        Date date = null;
        try {
            date = sdf.parse("2024/04/06");
        } catch (ParseException e) {
            e.printStackTrace();
        }
        List<Zone> zones = Arrays.asList(zone1);
        List<MultipartFile> images = createSampleMultipartFile();
        UpdateEventRequest request = new UpdateEventRequest(26L,"T1",8L,date,"15:05",1,zones,images);


        when(repository.updateEvent(Mockito.any(Event.class))).thenReturn(Mockito.any(Event.class));
        IEventService sut = new EventService(repository, storage);

        UpdateEventResponse response = sut.updateEvent(request);

        verify(repository, times(1)).updateEvent(Mockito.any(Event.class));
        assertNotNull(response);
    }
    @Test
    public void testFilterEvents() {
        EventRepository repository = mock(EventRepository.class);
        IEventRepository irepository = mock(IEventRepository.class);
        StorageService storage = mock(StorageService.class);
        String name = "Sade";
        List<Event> events = Collections.singletonList(new Event());
        when(repository.filterEvent(name)).thenReturn(events);
        IEventService sut = new EventService(repository, storage);

        GetEventResponse response = sut.filterEvents(name);

        verify(repository, times(1)).filterEvent(name);
        assertEquals(1, response.getEvent().size());
    }

    @Test
    public void testFindAll() {
        EventRepository repository = mock(EventRepository.class);
        StorageService storage = mock(StorageService.class);
        List<Event> events = Collections.singletonList(new Event());
        when(repository.findAll()).thenReturn(events);
        IEventService sut = new EventService(repository, storage);

        GetEventResponse response = sut.findAll();

        verify(repository, times(1)).findAll();
        assertEquals(1, response.getEvent().size());
    }

    @Test
    public void testGetEventById() {
        EventRepository repository = mock(EventRepository.class);
        StorageService storage = mock(StorageService.class);
        long id = 1L;
        Event event = new Event();
        when(repository.getEventbyId(id)).thenReturn(event);
        IEventService sut = new EventService(repository, storage);

        UpdateEventResponse response = sut.getEventbyId(id);

        verify(repository, times(1)).getEventbyId(id);
        assertNotNull(response);
    }

    @Test
    public void testGetNotCompleted() {
        EventRepository repository = mock(EventRepository.class);
        StorageService storage = mock(StorageService.class);
        List<Event> events = Collections.singletonList(new Event());
        when(repository.getNotCompleted()).thenReturn(events);
        IEventService sut = new EventService(repository, storage);
        GetEventResponse response = sut.getNotCompleted();

        verify(repository, times(1)).getNotCompleted();
        assertEquals(1, response.getEvent().size());
    }
    @Test
    public void testDeleteEvent() {
        // Arrange
        EventRepository repository = mock(EventRepository.class);
        StorageService storage = mock(StorageService.class);
        Zone zone1 = Zone.builder().id(1L).price(100).availableSeats(50).build();

        SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd");
        Date date = null;
        try {
            date = sdf.parse("2024/04/06");
        } catch (ParseException e) {
            e.printStackTrace();
        }
        List<Zone> zones = Arrays.asList(zone1);
        List<MultipartFile> images = createSampleMultipartFile();
        UpdateEventRequest request = new UpdateEventRequest(26L,"T1",8L,date,"15:05",1,zones,images); // Set necessary values
        Event event = EventConverterBusiness.updateEventRequestConverter(request);

        // Act
        doNothing().when(repository).deleteEvent(event); // Corrected line
        IEventService sut = new EventService(repository, storage);

        sut.deleteEvent(request);

        // Assert
        verify(repository, times(1)).deleteEvent(event);
    }



    @Test
    public void testGetEventsByPopularity() {
        // Arrange
        EventRepository repository = mock(EventRepository.class);
        StorageService storage = mock(StorageService.class);
        List<Event> events = Collections.singletonList(new Event());
        when(repository.getEventsByPopularity()).thenReturn(events);

        IEventService sut = new EventService(repository, storage);
        GetEventResponse response = sut.getEventsByPopularity();

        verify(repository, times(1)).getEventsByPopularity();
        assertEquals(1, response.getEvent().size());

    }

}
