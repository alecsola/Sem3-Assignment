package fontys.sem3.school.persistence;

import fontys.sem3.school.persistence.JPAmappers.EventJPAmapper;
import fontys.sem3.school.persistence.JPAmappers.TheatreJPAmapper;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;

@Repository
public interface IEventRepository extends JpaRepository<EventJPAmapper, String> {

    @Query("SELECT e FROM EventJPAmapper e " +
            "LEFT JOIN e.image i " +
            "WHERE (:name IS NULL OR LOWER(e.Name) LIKE LOWER(CONCAT('%', :name, '%'))) ")
    List<EventJPAmapper> findEventByName(
            @Param("name") String name
    );
    List<EventJPAmapper> findByCompleted(int Completed);

    List<EventJPAmapper> findByCompletedAndDateAfter(int completed, Date date);
}
