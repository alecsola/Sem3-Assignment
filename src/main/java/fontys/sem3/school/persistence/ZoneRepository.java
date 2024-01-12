package fontys.sem3.school.persistence;

import fontys.sem3.school.persistence.JPAmappers.UserJPAmapper;
import fontys.sem3.school.persistence.JPAmappers.ZoneJPAmapper;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ZoneRepository extends JpaRepository<ZoneJPAmapper, Long> {
}
