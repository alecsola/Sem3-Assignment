package fontys.sem3.school.business.servicesInterfaces;

import fontys.sem3.school.business.Request.PurchaseTicketRequest;

public interface ITicketService {
    long purchaseTicket(PurchaseTicketRequest request);
}
