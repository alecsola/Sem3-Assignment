package fontys.sem3.school.persistence.repository;


import fontys.sem3.school.business.interfaces.IEventRepository;
import fontys.sem3.school.business.interfaces.TheatreService;
import fontys.sem3.school.domain.Event;
import fontys.sem3.school.domain.Zone;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;

@Repository
@RequiredArgsConstructor
public class EventRepository implements IEventRepository {

    private final List<Event> events;
    private static Long NEXT_ID = 1L;

    public void setNextId(Long id) {
        NEXT_ID++;
    }
    public Event createEvent(Event event){
        event.setId(NEXT_ID);
        setNextId(NEXT_ID);
        events.add(event);
        return event;
    }
    public boolean deleteEvent (Event event){
        if (events.contains(event)){
            events.remove(event);
            return true;
        }
        else{
            throw new IllegalArgumentException("Event not found ");
        }
    }
    public Event getEventbyId(Long Id){
        for (Event event : events) {
            if (event.getId().equals(Id)) {
                return event;
            }

        }
        throw new IllegalArgumentException("Id not found" + Id);
    }
    public Event updateEvent(Long Id, String Name, Long theatreId, Date date, List<Zone> zones,int Completed){
        Event event = getEventbyId(Id);
        if (event!=null){
            event.setName(Name);
            event.setTheatreId(theatreId);
            event.setZones(zones);
            event.setCompleted(Completed);
            return event;
        }
        else{
            throw new IllegalArgumentException("Event not found");
        }

    }
    public Event updateStatus(Long Id,int Completed){
        Event event = getEventbyId(Id);
        if(event!=null){
            if(Completed == 2){
                events.remove(event);
                return null;
            }
            else{
                return event;
            }
        }
        else{
            throw new IllegalArgumentException("Event not found");
        }
    }



}
