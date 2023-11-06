package fontys.sem3.school.business.Converter;

import fontys.sem3.school.business.Request.Event.EventRequest;
import fontys.sem3.school.business.Request.Theatre.TheatreRequest;
import fontys.sem3.school.business.Response.EventResponse;
import fontys.sem3.school.business.Response.Theatre.TheatreResponse;
import fontys.sem3.school.domain.Event;
import fontys.sem3.school.domain.Theatre;

public class EventConverter {

    public Event eventRequestConverter(EventRequest request){
        Event event = new Event();
        event.setName(request.getName());
        event.setTheatreId(request.getTheatreId());
        event.setDate(request.getDate());
        event.setZones(request.getZones());
        event.setCompleted(request.getCompleted());

        return event;
    }

    public EventResponse eventResponse (Event event){
        EventResponse response = new EventResponse();
        response.setId(event.getId());
        return response;
    }
}
