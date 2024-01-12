package fontys.sem3.school.persistence.converters;

import fontys.sem3.school.domain.Event;
import fontys.sem3.school.domain.Ticket;
import fontys.sem3.school.persistence.JPAmappers.EventJPAmapper;
import fontys.sem3.school.persistence.JPAmappers.TicketJPAmapper;

import java.util.ArrayList;
import java.util.List;

public class TicketConverter {

    public static TicketJPAmapper convertToJPA(Ticket ticket) {
        if (ticket == null) {
            return null;
        }

        return TicketJPAmapper.builder()
                .Id(ticket.getId())
                .theatreId(ticket.getTheatreId())
                .zoneId(ticket.getZoneId())
                .eventId(ticket.getEventId())
                .price(ticket.getPrice())
                .userId(ticket.getUserId())
                .build();
    }


    public static Ticket convertToTicket(TicketJPAmapper ticketJPAmapper) {
        if (ticketJPAmapper == null) {
            return null;
        }
        return Ticket.builder()
                .Id(ticketJPAmapper.getId())
                .time(ticketJPAmapper.getTime())
                .date(ticketJPAmapper.getDate())
                .eventId(ticketJPAmapper.getEventId())
                .zoneId(ticketJPAmapper.getZoneId())
                .theatreId(ticketJPAmapper.getTheatreId())
                .userId(ticketJPAmapper.getUserId())
                .price(ticketJPAmapper.getPrice())
                .build();
    }
    public static List<Ticket> convertEventList (List<TicketJPAmapper> ticketJPAmappers) {
        List<Ticket> tickets = new ArrayList<>();
        for (TicketJPAmapper ticketJPAmapper : ticketJPAmappers) {
            Ticket ticket;

            ticket = new Ticket(ticketJPAmapper.getId(),ticketJPAmapper.getDate(), ticketJPAmapper.getTime(), ticketJPAmapper.getPrice(), ticketJPAmapper.getTheatreId(), ticketJPAmapper.getEventId(), ticketJPAmapper.getZoneId(), ticketJPAmapper.getUserId());
            tickets.add(ticket);



        }
        return tickets;
    }
}
