package fontys.sem3.school.business.impl;

import fontys.sem3.school.business.Converter.EventConverterBusiness;
import fontys.sem3.school.business.Request.Event.EventRequest;
import fontys.sem3.school.business.interfaces.IEventRepositoryBusiness;
import fontys.sem3.school.business.servicesInterfaces.IEventService;
import fontys.sem3.school.domain.Event;
import fontys.sem3.school.domain.Zone;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.sql.Time;
import java.util.Date;
import java.util.List;

@Service
@RequiredArgsConstructor
public class EventService implements IEventService{

    private IEventRepositoryBusiness repository;

    public long createEvent(EventRequest request) {
        Event event = EventConverterBusiness.eventRequestConverter(request);
        return repository.createEvent(event);
    }
}
