package fontys.sem3.school.persistence.JPAmappers;

import jakarta.persistence.*;
import lombok.*;

import java.util.Date;
import java.util.List;

@Entity
@Table(name = "ticket")
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class TicketJPAmapper {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long Id;
    private Date date;
    private String time;
    private int price;
    private Long theatreId;
    private Long eventId;
    private Long zoneId;
    private Long userId;
    private int ticketAmount;

}
