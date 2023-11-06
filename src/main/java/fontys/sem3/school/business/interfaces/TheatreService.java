package fontys.sem3.school.business.interfaces;

import fontys.sem3.school.domain.Theatre;

import java.util.List;
import java.util.Optional;

public interface TheatreService {
    Theatre createTheatre(Theatre theatre);

    List<Theatre> getTheatrebyFilter(Optional<String> Name, Optional<String> Country, Optional<String> City, Optional<Integer> SeatCapacity);
    Theatre updateTheatre(Long Id, String Name,String Details, String Country, String City, int Popularity, int Capacity);
    boolean deleteTheatre(Theatre theatre);
    Theatre getTheatrebyId (Long Id);
}
