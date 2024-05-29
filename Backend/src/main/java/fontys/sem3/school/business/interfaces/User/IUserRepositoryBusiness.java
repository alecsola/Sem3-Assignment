package fontys.sem3.school.business.interfaces.User;

import fontys.sem3.school.domain.User;

public interface IUserRepositoryBusiness {
    Long saveNewUser(User user);
    Long saveNewAdmin(User user);
    User findUserbyUsername(String Username);
    User getUser(Long id);
    User updateUser(User user);
}
