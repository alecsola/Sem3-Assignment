package fontys.sem3.school.business.Response;

import fontys.sem3.school.domain.Event;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

import java.util.List;

@Builder
@Getter
@AllArgsConstructor
public class GetEventResponse {
    private List<Event> event;
}
