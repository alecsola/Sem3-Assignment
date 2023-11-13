package fontys.sem3.school.persistence;

import fontys.sem3.school.persistence.JPAmappers.EventJPAmapper;
import fontys.sem3.school.persistence.JPAmappers.TheatreJPAmapper;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IEventRepository extends JpaRepository<EventJPAmapper, String> {
}
