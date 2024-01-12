package fontys.sem3.school.persistence.repository;


import fontys.sem3.school.business.Converter.Converter;
import fontys.sem3.school.business.Converter.EventConverterBusiness;
import fontys.sem3.school.business.interfaces.IEventRepositoryBusiness;
import fontys.sem3.school.domain.Event;
import fontys.sem3.school.domain.Theatre;
import fontys.sem3.school.domain.User;
import fontys.sem3.school.persistence.IEventRepository;
import fontys.sem3.school.persistence.JPAmappers.EventJPAmapper;
import fontys.sem3.school.persistence.JPAmappers.TheatreJPAmapper;
import fontys.sem3.school.persistence.JPAmappers.UserJPAmapper;
import fontys.sem3.school.persistence.converters.*;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Repository
@RequiredArgsConstructor
public class EventRepository  implements IEventRepositoryBusiness{

    private final IEventRepository repository;

    @Transactional
    public long createEvent(Event event){

        EventJPAmapper eventJPAmapper = EventConverter.convertToJPA(event);
        eventJPAmapper.getZone().forEach(zone -> {
            zone.setEvent(eventJPAmapper);
        });
        eventJPAmapper.getImage().forEach(image-> image.setEvent(eventJPAmapper));
        return repository.save(eventJPAmapper).getId();
    }
    @Transactional
    public Event updateEvent(Event newEvent){
        Event event = getEventbyId(newEvent.getId());

        EventJPAmapper eventJPAmapper = EventConverter.convertToJPA(event);
        if(eventJPAmapper!=null){
            eventJPAmapper.setName(newEvent.getName());
            eventJPAmapper.setDate(newEvent.getDate());
            eventJPAmapper.setTheatreId(newEvent.getTheatreId());
            eventJPAmapper.setTime(newEvent.getTime());
            eventJPAmapper.setCompleted(newEvent.getCompleted());
            eventJPAmapper.setImage(ImageConverter.mapToJpaFiles(newEvent.getImage()));
            eventJPAmapper.getImage().forEach(image-> image.setEvent(eventJPAmapper));
            eventJPAmapper.setZone(ZoneConverter.mapToJpaZones(newEvent.getZones()));
            eventJPAmapper.getZone().forEach(zone -> zone.setEvent(eventJPAmapper));
            repository.save(eventJPAmapper);
            return EventConverter.convertToEvent(eventJPAmapper);
        }
        return null;
    }
    @Transactional
    public List<Event> retrieveAllEvents(){
        List<EventJPAmapper> JPAevents;
        List<Event> events = new ArrayList<>();
        JPAevents = repository.findAll();
        for(EventJPAmapper JPAevent:JPAevents){
            Event event = EventConverter.convertToEvent(JPAevent);
            events.add(event);
        }
        return events;
    }

    @Transactional
    public List<Event>filterEvent(String name){
        List<EventJPAmapper> eventJPAmappers =  repository.findEventByName(name);
        return EventConverter.convertEventList(eventJPAmappers);
    }
    @Transactional
    public List<Event> findAll(){
        List<EventJPAmapper> eventJPAmappers = repository.findAll();
        return EventConverter.convertEventList(eventJPAmappers);
    }

    @Transactional
    public Event getEventbyId(long id){
        List<EventJPAmapper> eventJPAmappers = repository.findAll();
        for(EventJPAmapper eventJPAmapper:eventJPAmappers){
            if(eventJPAmapper.getId().equals(id)){
                return EventConverter.convertToEvent(eventJPAmapper);
            }
        }return null;
    }
    @Transactional
    public List<Event> getNotCompleted() {
        List<EventJPAmapper> notCompletedJPAmappers = repository.findByCompletedAndDateAfter(0,new Date());
        return EventConverter.convertEventList(notCompletedJPAmappers);
    }
    @Transactional
    public void updateCompletedStatus() {
        List<EventJPAmapper> events = repository.findAll();

        for (EventJPAmapper event : events) {
            if (event.getDate().before(new Date())) {
                // Update completed status to 1
                event.setCompleted(1);
                repository.save(event);
            }
        }
    }







}
