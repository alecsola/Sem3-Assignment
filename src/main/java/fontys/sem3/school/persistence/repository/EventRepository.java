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
import fontys.sem3.school.persistence.converters.EventConverter;
import fontys.sem3.school.persistence.converters.TheatreConverter;
import fontys.sem3.school.persistence.converters.UserConverter;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
@RequiredArgsConstructor
public class EventRepository  implements IEventRepositoryBusiness{

    private final IEventRepository repository;

    @Transactional
    public long createEvent(Event event){

        EventJPAmapper eventJPAmapper = EventConverter.convertToJPA(event);
        eventJPAmapper.getImage().forEach(image-> image.setEvent(eventJPAmapper));
        return repository.save(eventJPAmapper).getId();
    }

    @Transactional
    public Event getEventbyId(long id){
        List<EventJPAmapper> eventJPAmappers = repository.findAll();
        for(EventJPAmapper eventJPAmapper:eventJPAmappers){
            if(eventJPAmapper.getId().equals(id)){
                Event event = EventConverter.convertToEvent(eventJPAmapper);
                return event;
            }
        }return null;
    }






}
