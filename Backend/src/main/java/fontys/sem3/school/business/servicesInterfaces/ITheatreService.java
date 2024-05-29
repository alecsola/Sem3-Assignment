package fontys.sem3.school.business.servicesInterfaces;

import fontys.sem3.school.business.Request.Theatre.TheatreRequest;
import fontys.sem3.school.business.Response.Theatre.GetTheatreResponse;
import fontys.sem3.school.domain.Theatre;

import java.util.List;
import java.util.Optional;

public interface ITheatreService {
    long createTheatre(TheatreRequest theatre);
    GetTheatreResponse filterTheatres(String name, String city, String country);
    GetTheatreResponse findAll();
    Theatre getTheatrebyId(long id);
}
