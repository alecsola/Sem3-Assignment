package fontys.sem3.school.controller.Request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UpdateTheatreRequest {
    @NotNull
    private Long TheatreId;
    @NotBlank
    private String Name;
    @NotNull
    private int Seats;
}
