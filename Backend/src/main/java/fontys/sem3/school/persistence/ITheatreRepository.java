package fontys.sem3.school.persistence;

import fontys.sem3.school.persistence.JPAmappers.TheatreJPAmapper;
import fontys.sem3.school.persistence.JPAmappers.UserJPAmapper;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ITheatreRepository extends JpaRepository<TheatreJPAmapper, String> {
//
    @Query("SELECT t FROM TheatreJPAmapper t " +
            "LEFT JOIN t.image i " +
            "WHERE (:name IS NULL OR LOWER(t.Name) LIKE LOWER(CONCAT('%', :name, '%'))) " +
            "AND (:city IS NULL OR LOWER(t.City) LIKE LOWER(CONCAT('%', :city, '%'))) " +
            "AND (:country IS NULL OR LOWER(t.Country) LIKE LOWER(CONCAT('%', :country, '%')))")
    List<TheatreJPAmapper> findTheatresByFilter(
            @Param("name") String name,
            @Param("city") String city,
            @Param("country") String country
    );

}
