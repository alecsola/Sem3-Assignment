package fontys.sem3.school.persistence.converters;

import fontys.sem3.school.domain.Event;
import fontys.sem3.school.domain.Theatre;
import fontys.sem3.school.persistence.JPAmappers.EventJPAmapper;
import fontys.sem3.school.persistence.JPAmappers.ImageJPAmapper;
import fontys.sem3.school.persistence.JPAmappers.TheatreJPAmapper;
import fontys.sem3.school.persistence.JPAmappers.ZoneJPAmapper;

import java.util.ArrayList;
import java.util.List;

public class EventConverter {


    public static EventJPAmapper convertToJPA(Event event) {
        if (event == null) {
            return null;
        }

        return EventJPAmapper.builder()
                .Id(event.getId())
                .Name(event.getName())
                .theatreId(event.getTheatreId())
                .date(event.getDate())
                .time(event.getTime())
                .zone(ZoneConverter.mapToJpaZones(event.getZones()))
                .completed(event.getCompleted())
                .image(ImageConverter.mapToJpaFiles(event.getImage()))
                .build();
    }


    public static Event convertToEvent(EventJPAmapper eventJPAmapper) {
        if (eventJPAmapper == null) {
            return null;
        }
        return Event.builder()
                .Id(eventJPAmapper.getId())
                .Name(eventJPAmapper.getName())
                .theatreId(eventJPAmapper.getTheatreId())
                .date(eventJPAmapper.getDate())
                .time(eventJPAmapper.getTime())
                .zones(ZoneConverter.mapToZones(eventJPAmapper.getZone()))
                .Completed(eventJPAmapper.getCompleted())
                .image(ImageConverter.mapToFiles(eventJPAmapper.getImage()))
                .build();
    }
    public static List<Event> convertEventList (List<EventJPAmapper> eventJPAmappers) {
        List<Event> events = new ArrayList<>();
        for (EventJPAmapper eventJPAmapper : eventJPAmappers) {
            Event event;

            event = new Event(eventJPAmapper.getId(), eventJPAmapper.getName(),eventJPAmapper.getTheatreId(),  eventJPAmapper.getDate(),  eventJPAmapper.getTime(), ZoneConverter.mapToZones(eventJPAmapper.getZone()),ImageConverter.mapToFiles(eventJPAmapper.getImage()),eventJPAmapper.getCompleted() );
            events.add(event);



        }
        return events;
    }


}
