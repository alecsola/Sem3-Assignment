package fontys.sem3.school.business.servicesInterfaces;

import fontys.sem3.school.business.Request.Event.EventRequest;
import fontys.sem3.school.business.Request.Event.UpdateEventRequest;
import fontys.sem3.school.business.Response.GetEventResponse;
import fontys.sem3.school.business.Response.UpdateEventResponse;
import fontys.sem3.school.domain.Event;
import fontys.sem3.school.domain.Zone;

import java.util.Date;
import java.util.List;

public interface IEventService {
    long createEvent(EventRequest request);
    UpdateEventResponse updateEvent(UpdateEventRequest request);
    GetEventResponse filterEvents(String name);
    GetEventResponse findAll();
    UpdateEventResponse getEventbyId(long id);
    GetEventResponse getNotCompleted();

}
