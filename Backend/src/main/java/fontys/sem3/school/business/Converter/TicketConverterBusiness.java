package fontys.sem3.school.business.Converter;

import fontys.sem3.school.business.Request.Event.EventRequest;
import fontys.sem3.school.business.Request.Event.UpdateEventRequest;
import fontys.sem3.school.business.Request.PurchaseTicketRequest;
import fontys.sem3.school.business.Response.UpdateEventResponse;
import fontys.sem3.school.domain.Event;
import fontys.sem3.school.domain.Image;
import fontys.sem3.school.domain.Ticket;
import fontys.sem3.school.domain.Zone;

import java.util.ArrayList;
import java.util.List;

public class TicketConverterBusiness {
    public static Ticket ticketRequestConverter(PurchaseTicketRequest request){
        Ticket ticket = new Ticket();
        ticket.setDate(request.getDate());
        ticket.setTime(request.getTime());
        ticket.setPrice(request.getPrice());
        ticket.setEventId(request.getEventId());
        ticket.setUserId(request.getUserId());
        ticket.setZoneId(request.getZoneId());
        ticket.setTheatreId(request.getTheatreId());
        ticket.setTicketAmount(request.getAmountTicket());
        return ticket;
    }

    public static UpdateEventResponse updateEventResponseConverter(Event event){

        Event newEvent = new Event(event.getId(),event.getName(), event.getTheatreId(),event.getDate(),event.getTime(),
                event.getZones(),event.getImage(),event.getCompleted());
        return new UpdateEventResponse(newEvent);
    }
}
