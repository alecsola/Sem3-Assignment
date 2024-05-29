package fontys.sem3.school.business.Response;


import fontys.sem3.school.domain.Event;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UpdateEventResponse {
    private Event event;
}
