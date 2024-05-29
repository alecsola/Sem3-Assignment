package fontys.sem3.school.persistence.JPAmappers;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "EventZone")
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class EventZoneJPAmapper {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "event_id")
    private EventJPAmapper event;

    @ManyToOne
    @JoinColumn(name = "zone_id")
    private ZoneJPAmapper zone;
}
