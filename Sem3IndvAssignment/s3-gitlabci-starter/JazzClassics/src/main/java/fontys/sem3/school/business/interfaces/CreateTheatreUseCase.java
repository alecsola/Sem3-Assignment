package fontys.sem3.school.business.interfaces;

import fontys.sem3.school.domain.CreateTheatreRequest;
import fontys.sem3.school.domain.CreateTheatreResponse;

public interface CreateTheatreUseCase {
CreateTheatreResponse createTheatre(CreateTheatreRequest request);
}
