package fontys.sem3.school.business.interfaces;

import fontys.sem3.school.domain.Event;
import org.springframework.stereotype.Repository;

@Repository
public interface IEventRepositoryBusiness {

    long createEvent(Event event);
}
