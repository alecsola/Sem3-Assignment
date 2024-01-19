package fontys.sem3.school.business.Request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PurchaseTicketRequest {
    private Date date;
    private String time;
    private int price;
    private Long theatreId;
    private Long eventId;
    private Long zoneId;
    private Long userId;
    private int amountTicket;
}
