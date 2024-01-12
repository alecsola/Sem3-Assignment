package fontys.sem3.school.business.interfaces;

import fontys.sem3.school.domain.Ticket;

public interface ITicketBusinessRepository {

    long purchaseTicket(Ticket ticket);
}
