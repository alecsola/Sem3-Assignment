package fontys.sem3.school.business.interfaces;

import fontys.sem3.school.business.Converter.TicketConverterBusiness;
import fontys.sem3.school.business.Request.PurchaseTicketRequest;
import fontys.sem3.school.business.Response.PurchaseTicketResponse;
import fontys.sem3.school.business.Response.User.GetUserResponse;
import fontys.sem3.school.business.interfaces.ITicketBusinessRepository;
import fontys.sem3.school.business.interfaces.User.IUserService;
import fontys.sem3.school.business.servicesInterfaces.ITicketService;
import fontys.sem3.school.domain.Ticket;
import fontys.sem3.school.domain.User;
import fontys.sem3.school.persistence.converters.UserConverter;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor

public class TicketService implements ITicketService {

    private final ITicketBusinessRepository repository;
    private final IUserService userService;
    private final IEmailService emailService;


    public long purchaseTicket(PurchaseTicketRequest request){
        Ticket ticket = TicketConverterBusiness.ticketRequestConverter(request);
        long result = repository.purchaseTicket(ticket);
        if (result > 0) {
            sendClientEmail(ticket);
        }
        return new PurchaseTicketResponse(result).getId();
    }


    private void sendClientEmail(Ticket ticket){
        GetUserResponse userResponse = userService.getUser(ticket.getUserId());
        User user = userResponse.getUser();

        String subject = "Booking Confirmation";
        StringBuilder seatList = new StringBuilder();

        String htmlTemplate = """
        <html>
            <body>
                <h1>Booking Confirmation</h1>
                <p>Dear %s, your booking has been confirmed.</p>

                <h3>Booking Details</h3>
                
                  <p>Price: $%d</p>
                <strong>Selected Zone:</strong>
                <ul>
                    %s
                </ul>

            </body>
        </html>
        """;

        String formattedHtml = String.format(htmlTemplate, user.getName(), ticket.getPrice(), ticket.getZoneId());

        emailService.sendBookingConfirmation(user.getEmail(), subject, formattedHtml);
    }

}
