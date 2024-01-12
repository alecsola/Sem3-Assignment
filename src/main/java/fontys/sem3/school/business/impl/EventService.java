package fontys.sem3.school.business.impl;

import fontys.sem3.school.business.Converter.EventConverterBusiness;
import fontys.sem3.school.business.Request.Event.EventRequest;
import fontys.sem3.school.business.Request.Event.UpdateEventRequest;
import fontys.sem3.school.business.Response.EventResponse;
import fontys.sem3.school.business.Response.GetEventResponse;
import fontys.sem3.school.business.Response.Theatre.TheatreResponse;
import fontys.sem3.school.business.Response.UpdateEventResponse;
import fontys.sem3.school.business.interfaces.IEventRepositoryBusiness;
import fontys.sem3.school.business.servicesInterfaces.IEventService;
import fontys.sem3.school.domain.Event;
import fontys.sem3.school.domain.Zone;
import jakarta.annotation.security.RolesAllowed;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.sql.Time;
import java.util.Date;
import java.util.List;

@Service
@RequiredArgsConstructor
public class EventService implements IEventService{

    private final IEventRepositoryBusiness repository;
    private final StorageService storageService;

    public long createEvent(EventRequest request) {
        Event event = EventConverterBusiness.eventRequestConverter(request);
        long result = repository.createEvent(event);
        if (result != 0) {
            for (MultipartFile file : request.getImage()) {
                storageService.storeEvent(file, result);
            }
        }

        return new EventResponse(result).getId();
    }
    public UpdateEventResponse updateEvent(UpdateEventRequest request){
        Event event = EventConverterBusiness.updateEventRequestConverter(request);
        Event newEvent = repository.updateEvent(event);
        if (newEvent != null) {
            for (MultipartFile file : request.getImage()) {
                storageService.storeEvent(file, newEvent.getId());
            }
        }
        return EventConverterBusiness.updateEventResponseConverter(event);

    }
    public GetEventResponse filterEvents(String name){
        List<Event> events;
        events = repository.filterEvent(name);
        return new GetEventResponse(events);
    }
    public GetEventResponse findAll(){
        List<Event> events;
        events = repository.findAll();
        return new GetEventResponse(events);
    }
    public UpdateEventResponse getEventbyId(long id){
        Event event = repository.getEventbyId(id);
        return EventConverterBusiness.updateEventResponseConverter(event);
    }
    public GetEventResponse getNotCompleted(){
        List<Event>events;
        events = repository.getNotCompleted();
        return new GetEventResponse(events);
    }


}
