package fontys.sem3.school.business.Response.Theatre;


import fontys.sem3.school.domain.Theatre;
import lombok.*;

import java.util.List;


@Builder
@Getter
@AllArgsConstructor
public class GetTheatreResponse {
    private List<Theatre> theatres;
}
