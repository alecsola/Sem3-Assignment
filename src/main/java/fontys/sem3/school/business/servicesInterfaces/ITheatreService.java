package fontys.sem3.school.business.servicesInterfaces;

import fontys.sem3.school.business.Request.Theatre.TheatreRequest;
import fontys.sem3.school.domain.Theatre;

import java.util.List;
import java.util.Optional;

public interface ITheatreService {
    long createTheatre(TheatreRequest theatre);
    List<Theatre> filterTheatres(String name, String city, String country);

}
