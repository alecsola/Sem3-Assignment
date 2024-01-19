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
    private final IEmailService emailService;


    public long purchaseTicket(PurchaseTicketRequest request){
        Ticket ticket = TicketConverterBusiness.ticketRequestConverter(request);
        long result = repository.purchaseTicket(ticket);
        return new PurchaseTicketResponse(result).getId();
    }
//    private void sendClientEmail(Ticket ticket){
//        String subject = "Booking Confirmation";
//        StringBuilder seatList = new StringBuilder();
//
//
//        String htmlTemplate = """
//                <html>
//                    <body>
//                        <h1>Booking Confirmation</h1>
//                        <p>Dear %s, your booking has been confirmed.</p>
//
//                        <h3>Booking Details</h3>
//                        <p>Status: %s</p>
//                        <p>Price: %s</p>
//                        <strong>Selected Seats:</strong>
//                        <ul>
//                            %s
//                        </ul>
//
//                    </body>
//                </html>
//                """;
//
//        String formattedHtml = String.format(htmlTemplate, ticket.getUser().getFirstName(), booking.getStatus(), String.format("%.2f", booking.getPrice()),seatList);
//        sendEmailUseCase.sendBookingConfirmation(booking.getUser().getEmail(), subject, formattedHtml);
//    }
}
