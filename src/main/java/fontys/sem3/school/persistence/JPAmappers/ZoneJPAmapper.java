package fontys.sem3.school.persistence.JPAmappers;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "zone")
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor

public class ZoneJPAmapper {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long Id;
    private int Price;
    private int AvailableSeats;
    @ManyToOne
    @JoinColumn(name = "event_id")
    private EventJPAmapper event;
}