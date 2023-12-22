package fontys.sem3.school.persistence.JPAmappers;

import fontys.sem3.school.domain.Zone;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.sql.Time;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "event")
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor

public class EventJPAmapper {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long Id;
    private String Name;
    private Long theatreId;
    private Date date;
    private Time time;
    @OneToMany(mappedBy = "event", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<ZoneJPAmapper> zone;
    private int Completed;
    @OneToMany(mappedBy = "event", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<ImageJPAmapper> image;

}
