package fontys.sem3.school.persistence;

import fontys.sem3.school.persistence.JPAmappers.TheatreJPAmapper;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ITheatreRepository extends JpaRepository<TheatreJPAmapper, String> {
//    @Query("SELECT t FROM TheatreJPAmapper t WHERE t.Name = :name AND t.City = :city AND t.Country = :country")
//    TheatreJPAmapper findByNameAndCityAndCountry(
//            @Param("Name") String name,
//            @Param("City") String city,
//            @Param("Country") String country
//    );
//
//    @Query("SELECT p FROM TheatreJPAmapper p WHERE LOWER(p.Name) LIKE LOWER(CONCAT('%', :Name, '%'))")
//    List<TheatreJPAmapper> findTheatreByName(@Param("Name") String Name);
}
