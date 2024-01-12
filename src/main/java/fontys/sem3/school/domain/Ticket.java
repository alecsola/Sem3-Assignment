package fontys.sem3.school.domain;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Ticket {
    private Long Id;
    private Date date;
    private String time;
    private int price;
    private Long theatreId;
    private Long eventId;
    private Long zoneId;
    private Long userId;
}
