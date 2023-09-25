package fontys.sem3.school.business.interfaces;

import fontys.sem3.school.controller.Request.CreateTheatreRequest;
import fontys.sem3.school.controller.Response.CreateTheatreResponse;

public interface CreateTheatreUseCase {
CreateTheatreResponse createTheatre(CreateTheatreRequest request);
}
