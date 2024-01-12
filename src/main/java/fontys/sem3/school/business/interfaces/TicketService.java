package fontys.sem3.school.business.interfaces;

import fontys.sem3.school.business.Converter.TicketConverterBusiness;
import fontys.sem3.school.business.Request.PurchaseTicketRequest;
import fontys.sem3.school.business.Response.PurchaseTicketResponse;
import fontys.sem3.school.business.interfaces.ITicketBusinessRepository;
import fontys.sem3.school.business.servicesInterfaces.ITicketService;
import fontys.sem3.school.domain.Ticket;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor

public class TicketService implements ITicketService {

    private final ITicketBusinessRepository repository;


    public long purchaseTicket(PurchaseTicketRequest request){
        Ticket ticket = TicketConverterBusiness.ticketRequestConverter(request);
        long result = repository.purchaseTicket(ticket);
        return new PurchaseTicketResponse(result).getId();
    }
}
