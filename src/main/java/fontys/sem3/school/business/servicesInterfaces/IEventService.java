package fontys.sem3.school.business.servicesInterfaces;

import fontys.sem3.school.business.Request.Event.EventRequest;
import fontys.sem3.school.domain.Event;
import fontys.sem3.school.domain.Zone;

import java.util.Date;
import java.util.List;

public interface IEventService {
    long createEvent(EventRequest request);
}
