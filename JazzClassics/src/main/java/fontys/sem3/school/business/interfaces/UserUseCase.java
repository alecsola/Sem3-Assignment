package fontys.sem3.school.business.interfaces;

import fontys.sem3.school.business.Response.GetUserResponse;
import fontys.sem3.school.domain.User;

public interface UserUseCase {

    User createUser (User user);
    boolean deleteUser(User user);
    User updateUser(Long Id, String Name,String Email, String Username, String Password);
    GetUserResponse GetUser();
    User ValidateUserCredentials(String username, String password);

}
