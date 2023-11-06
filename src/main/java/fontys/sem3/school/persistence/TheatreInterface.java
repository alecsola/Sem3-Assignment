package fontys.sem3.school.persistence;

import fontys.sem3.school.persistence.JPAmappers.TheatreJPAmapper;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface TheatreInterface {
    @Query("SELECT t FROM TheatreJPAmapper t WHERE t.name = :name AND t.city = :city AND t.country = :country")
    TheatreJPAmapper findByNameAndCityAndCountry(
            @Param("name") String name,
            @Param("city") String city,
            @Param("country") String country
    );
}
