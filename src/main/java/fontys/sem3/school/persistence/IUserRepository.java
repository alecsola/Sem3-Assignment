package fontys.sem3.school.persistence;

import fontys.sem3.school.domain.User;
import fontys.sem3.school.persistence.JPAmappers.TheatreJPAmapper;
import fontys.sem3.school.persistence.JPAmappers.UserJPAmapper;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface IUserRepository extends JpaRepository<UserJPAmapper, String> {
    UserJPAmapper findByUsername(String username);
    @Query("SELECT u FROM UserJPAmapper u LEFT JOIN FETCH u.roles WHERE u.username = :username")
    UserJPAmapper findUserbyUsernameWithRoles(@Param("username") String username);
    UserJPAmapper findById(Long id);
}
