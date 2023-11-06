package fontys.sem3.school.business.interfaces;

import fontys.sem3.school.domain.Event;
import fontys.sem3.school.domain.Zone;

import java.util.Date;
import java.util.List;

public interface IEventRepository {

    Event createEvent(Event event);
    boolean deleteEvent (Event event);
    Event getEventbyId(Long Id);
    Event updateEvent(Long Id, String Name, Long theatreId, Date date, List<Zone> zones, int Completed);
    Event updateStatus(Long Id,int Completed);
}
