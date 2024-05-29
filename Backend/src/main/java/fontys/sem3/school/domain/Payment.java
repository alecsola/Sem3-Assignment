package fontys.sem3.school.domain;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Payment {
    private Long Id;
    private Long userId;
    private Long zoneId;
    private Long EventId;
    private int ticketId;
}
