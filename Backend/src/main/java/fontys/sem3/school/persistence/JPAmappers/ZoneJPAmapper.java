package fontys.sem3.school.persistence.JPAmappers;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "zone")
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor

public class ZoneJPAmapper {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long Id;
    private int Price;
    @Column(name = "available_seats")
    private int AvailableSeats;
    @ManyToOne
    @JoinColumn(name = "event_id")
    private EventJPAmapper event;
}