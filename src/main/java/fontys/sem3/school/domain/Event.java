package fontys.sem3.school.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

import java.sql.Time;
import java.util.Date;
import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor

public class Event {
    private Long Id;
    private String Name;
    private long theatreId;
    private Date date;
    private Time time;
    private List<Zone> zones;
    private List<Image> image;
    private int Completed;
}
