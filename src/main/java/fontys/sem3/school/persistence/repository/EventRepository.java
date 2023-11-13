package fontys.sem3.school.persistence.repository;


import fontys.sem3.school.business.Converter.Converter;
import fontys.sem3.school.business.interfaces.IEventRepositoryBusiness;
import fontys.sem3.school.domain.Event;
import fontys.sem3.school.persistence.IEventRepository;
import fontys.sem3.school.persistence.JPAmappers.EventJPAmapper;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@RequiredArgsConstructor
public class EventRepository  {


    private final List<Event> events;
    private final IEventRepository repository;
    private final Converter converter;






}
