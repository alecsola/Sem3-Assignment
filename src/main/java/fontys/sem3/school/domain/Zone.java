package fontys.sem3.school.domain;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Zone {
    private Long Id;
    private int Price;
    private int AvailableSeats;
}
