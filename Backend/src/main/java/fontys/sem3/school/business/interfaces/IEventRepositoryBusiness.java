package fontys.sem3.school.business.interfaces;

import fontys.sem3.school.domain.Event;
import fontys.sem3.school.persistence.JPAmappers.EventJPAmapper;
import org.springframework.stereotype.Repository;

import java.util.List;


public interface IEventRepositoryBusiness {

    long createEvent(Event event);
    Event updateEvent(Event newEvent);
    List<Event> retrieveAllEvents();
    List<Event> getNotCompleted();
    List <Event> findAll();
    Event getEventbyId(long id);
    List<Event>filterEvent(String name);
    void deleteEvent (Event newEvent);
    List<Event> getEventsByPopularity();
}
