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
    private Long id;
    private int price;
    private int availableSeats;

    @Override
    public String toString() {
        return "{" +
                "\"id\":" + id +
                ", \"price\":" + price +
                ", \"availableSeats\":" + availableSeats +
                '}';
    }
}
