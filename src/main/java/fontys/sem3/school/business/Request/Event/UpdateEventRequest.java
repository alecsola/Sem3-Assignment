package fontys.sem3.school.business.Request.Event;

import fontys.sem3.school.domain.Zone;
import lombok.Data;

import java.util.Date;
import java.util.List;

@Data
public class UpdateEventRequest {

    private Long Id;
    private String Name;
    private Long theatreId;
    private Date date;
    private List<Zone> zones;//supongo que esto sera el numero de zonas que tiene.
    private int Completed;
}
