package fontys.sem3.school.business.interfaces;

import fontys.sem3.school.business.Response.GetUserResponse;
import fontys.sem3.school.domain.User;

import java.util.List;

public interface UserUseCase {

    User createUser (User user);
    boolean deleteUser(User user);
    User updateUser(Long Id, String Name,String Username,String Email , String Password);
    List<User> GetUser();
    User getUserbyId(Long id);


}
