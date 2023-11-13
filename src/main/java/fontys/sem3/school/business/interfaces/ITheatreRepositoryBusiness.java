package fontys.sem3.school.business.interfaces;

import fontys.sem3.school.domain.Theatre;

import java.util.List;
import java.util.Optional;

public interface ITheatreRepositoryBusiness {

    long createTheatre(Theatre theatre);
    List<Theatre> filterTheatres(String name, String city, String country);

}
