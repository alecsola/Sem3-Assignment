package fontys.sem3.school.business;

import fontys.sem3.school.domain.User;

import java.util.List;

public interface IUserRepository {
    User findbyId(Long id);

    List<User> GetUser();

    User saveUser(User user);
}
