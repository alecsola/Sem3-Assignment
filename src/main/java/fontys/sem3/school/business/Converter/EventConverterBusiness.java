package fontys.sem3.school.business.Converter;

import fontys.sem3.school.business.Request.Event.EventRequest;
import fontys.sem3.school.business.Request.Event.UpdateEventRequest;
import fontys.sem3.school.business.Response.EventResponse;
import fontys.sem3.school.business.Response.UpdateEventResponse;
import fontys.sem3.school.domain.Event;
import fontys.sem3.school.domain.Image;
import fontys.sem3.school.domain.Zone;

import java.util.ArrayList;
import java.util.List;

public class EventConverterBusiness {

    public static Event eventRequestConverter(EventRequest request){
        Event event = new Event();
        event.setName(request.getName());
        event.setTheatreId(request.getTheatreId());
        event.setDate(request.getDate());
        event.setTime(request.getTime());

        // Step 3 & 4: Create Zone Objects and Associate with Event
        List<Zone> zones = new ArrayList<>();
        for (Zone zoneRequest : request.getZones()) {
            Zone zone = new Zone();
            zone.setPrice(zoneRequest.getPrice());
            zone.setAvailableSeats(zoneRequest.getAvailableSeats());
            zones.add(zone);
        }
        event.setZones(zones);
        List<Image> images = ImageConverter.convertToImageObject(request.getImage());
        event.setImage(images);
        return event;
    }
    public static Event updateEventRequestConverter(UpdateEventRequest request){
        Event event = new Event();
        event.setId(request.getId());
        event.setName(request.getName());
        event.setTheatreId(request.getTheatreId());
        event.setDate(request.getDate());
        event.setTime(request.getTime());
        event.setCompleted(request.getCompleted());
        // Step 3 & 4: Create Zone Objects and Associate with Event
        List<Zone> zones = new ArrayList<>();
        for (Zone zoneRequest : request.getZones()) {
            Zone zone = new Zone();
            zone.setPrice(zoneRequest.getPrice());
            zone.setAvailableSeats(zoneRequest.getAvailableSeats());
            zones.add(zone);
        }
        event.setZones(zones);
        List<Image> images = ImageConverter.convertToImageObject(request.getImage());
        event.setImage(images);
        return event;
    }



    public static UpdateEventResponse updateEventResponseConverter(Event event){

        Event newEvent = new Event(event.getId(),event.getName(), event.getTheatreId(),event.getDate(),event.getTime(),
                event.getZones(),event.getImage(),event.getCompleted());
        return new UpdateEventResponse(newEvent);
    }

    public EventResponse eventResponse (Event event){
        EventResponse response = new EventResponse();
        response.setId(event.getId());
        return response;
    }
}
