package fontys.sem3.school.business.impl;

import fontys.sem3.school.business.interfaces.IEventRepositoryBusiness;
import fontys.sem3.school.business.servicesInterfaces.IEventService;
import fontys.sem3.school.domain.Event;
import fontys.sem3.school.domain.Zone;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
@RequiredArgsConstructor
public class EventService  {



//    public Event createEvent(Event event){
//        try{
//            eventRepository.createEvent(event);
//            return event;
//        }
//        catch (Exception e){
//            throw new IllegalArgumentException("Theatre not found");
//        }
//    }
//    public boolean deleteEvent(Event event) {
//        if (eventRepository.deleteEvent(event)) {
//            return true;
//        }
//        return false;
//    }
//    public Event getEventbyId(Long Id){
//        Event event = eventRepository.getEventbyId(Id);
//        if (event!=null){
//            return event;
//        }
//        throw new IllegalArgumentException("Theatre not found" + Id);
//    }
//    public Event updateEvent(Long Id, String Name, Long theatreId, Date date, List<Zone> zones, int Completed){
//        try{
//            Event event = eventRepository.updateEvent(Id, Name, theatreId, date, zones,Completed);
//            return event;
//        }
//        catch (Exception e){
//            throw new IllegalArgumentException("Theatre not found" + Id);
//        }
//
//    }
//    public Event updateStatus(Long Id,int Completed){
//        try{
//            Event event = eventRepository.updateStatus(Id, Completed);
//            if (event !=null){
//                return event;
//            }
//            return null;
//
//        }
//        catch(Exception e){
//            throw new IllegalArgumentException("Something went wrong");
//        }
//    }
}
