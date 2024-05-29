package fontys.sem3.school.persistence.repository;


import fontys.sem3.school.business.interfaces.ITicketBusinessRepository;
import fontys.sem3.school.domain.Theatre;
import fontys.sem3.school.domain.Ticket;
import fontys.sem3.school.persistence.ITicketRepository;
import fontys.sem3.school.persistence.JPAmappers.TheatreJPAmapper;
import fontys.sem3.school.persistence.JPAmappers.TicketJPAmapper;
import fontys.sem3.school.persistence.JPAmappers.ZoneJPAmapper;
import fontys.sem3.school.persistence.ZoneRepository;
import fontys.sem3.school.persistence.converters.TheatreConverter;
import fontys.sem3.school.persistence.converters.TicketConverter;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
@RequiredArgsConstructor
public class TicketRepository implements ITicketBusinessRepository {
    private final ITicketRepository repository;
    private final ZoneRepository zoneRepository;
    @Transactional
    public long purchaseTicket(Ticket ticket){
        TicketJPAmapper ticketJPAmapper = TicketConverter.convertToJPA(ticket);
        ZoneJPAmapper zoneJPAmapper = zoneRepository.findById(ticketJPAmapper.getZoneId()).orElseThrow(
                () -> new RuntimeException("Zone not found")
        );
        zoneJPAmapper.setAvailableSeats(zoneJPAmapper.getAvailableSeats() - ticket.getTicketAmount());
        zoneRepository.save(zoneJPAmapper);
        return repository.save(ticketJPAmapper).getId();
    }

}
