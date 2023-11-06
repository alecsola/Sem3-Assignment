package fontys.sem3.school.persistence.repository;


import fontys.sem3.school.business.interfaces.ITheatreRepository;
import fontys.sem3.school.domain.Theatre;
import fontys.sem3.school.persistence.JPAmappers.TheatreJPAmapper;
import lombok.RequiredArgsConstructor;
import org.flywaydb.core.internal.jdbc.JdbcTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;
import java.util.function.Predicate;
import java.util.stream.Collectors;

@Repository
@RequiredArgsConstructor
public class TheatreRepository/* implements ITheatreRepository*/ {

    private final List<Theatre> theatres;
   /* @Autowired
    //private JdbcTemplate jdbcTemplate;
   // public Theatre createTheatre(Theatre theatre){
        return theatre;
    }*/



    public TheatreJPAmapper findByNameAndCityAndCountry(String name, String city, String country) {
        String query = "SELECT * FROM theatre WHERE name = ? AND city = ? AND country = ?";
        return null;//jdbcTemplate.queryForObject(query, new Object[]{name, city, country}, new TheatreJPAmapper());
    }


    public List<Theatre> getTheatrebyFilter(Optional<String> Name, Optional<String> Country, Optional<String> City, Optional<Integer> SeatCapacity){


        Predicate<Theatre> filter = theatre ->
                (Name.isEmpty() || theatre.getName().equalsIgnoreCase(Name.get())) &&
                        (Country.isEmpty() || theatre.getCountry().equalsIgnoreCase(Country.get())) &&
                        (City.isEmpty() || theatre.getCity().equalsIgnoreCase(City.get())) &&
                        (SeatCapacity.isEmpty() || theatre.getCapacity() == SeatCapacity.get());

        return theatres.stream()
                .filter(filter)
                .collect(Collectors.toList());

    }
    public Theatre updateTheatre(Long Id, String Name,String Details, String Country, String City, int Popularity, int Capacity){
        Theatre theatre = getTheatrebyId(Id);
        if(theatre!=null){
            theatre.setName(Name);
            theatre.setCountry(Country);
            theatre.setCity(City);
            theatre.setDetails(Details);
            theatre.setPopularity(Popularity);
            theatre.setCapacity(Capacity);

            return theatre;
        }
        else{
            throw new IllegalArgumentException("Theatre not found with ID: " + Id);
        }
    }
    public boolean deleteTheatre(Theatre theatre){
        if(theatres.contains(theatre)){
            theatres.remove(theatre);
            return true;
        }
        else{
            throw new IllegalArgumentException("Theatre not found");
        }


    }
    public Theatre getTheatrebyId (Long Id){
        for(Theatre theatre:theatres){
            if (theatre.getId().equals(Id)){
                return theatre;
            }
        }
        throw new IllegalArgumentException("Theatre not found with ID: " + Id);
    }

}
