package fontys.sem3.school.controller;


import fontys.sem3.school.business.Request.PurchaseTicketRequest;
import fontys.sem3.school.business.servicesInterfaces.ITicketService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.Date;

@RestController
@RequestMapping("/ticket")
@AllArgsConstructor
@CrossOrigin(origins = "http://localhost:5173")
public class TicketController {
    private final ITicketService service;

    @PostMapping("/purchase")
    public long purchaseTicket(Date date, @RequestParam String time, @RequestParam int price , @RequestParam Long theatreId,  @RequestParam Long eventId , @RequestParam Long zoneId , @RequestParam Long userId){
        PurchaseTicketRequest request = new PurchaseTicketRequest(date,time, price, theatreId, eventId,zoneId,userId);
        return service.purchaseTicket(request);
    }
}
