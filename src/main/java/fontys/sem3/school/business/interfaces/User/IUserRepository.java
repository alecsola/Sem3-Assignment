package fontys.sem3.school.business.interfaces.User;

import fontys.sem3.school.domain.User;

import java.util.List;

public interface IUserRepository {
    User getUserbyId(Long id);

    List<User> GetUser();

    User saveUser(User user);
}
