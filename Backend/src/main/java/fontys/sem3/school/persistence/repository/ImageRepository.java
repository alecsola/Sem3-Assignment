package fontys.sem3.school.persistence.repository;

import fontys.sem3.school.persistence.JPAmappers.ImageJPAmapper;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ImageRepository extends JpaRepository<ImageJPAmapper, Long> {
    void deleteByEvent_Id(long eventId);
}
