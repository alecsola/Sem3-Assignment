package fontys.sem3.school.business.interfaces;

import fontys.sem3.school.business.Request.CreateTheatreRequest;
import fontys.sem3.school.business.Response.CreateTheatreResponse;

public interface CreateTheatreUseCase {
CreateTheatreResponse createTheatre(CreateTheatreRequest request);
}
