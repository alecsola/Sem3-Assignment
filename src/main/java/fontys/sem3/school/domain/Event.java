package fontys.sem3.school.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;
import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor

public class Event {
    private Long Id;
    private String Name;
    private Long theatreId;
    private Date date;
    private List<Zone> zones;//supongo que esto sera el numero de zonas que tiene.
    private int Completed;
    private String Image;
}
