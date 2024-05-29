package fontys.sem3.school.business.interfaces.User;

import fontys.sem3.school.business.Request.User.CreateUserRequest;
import fontys.sem3.school.business.Response.User.GetUserResponse;
import fontys.sem3.school.domain.User;

import java.util.List;

public interface IUserService {
    Long saveNewUser(CreateUserRequest request);
    GetUserResponse getUser (long id);
    Long saveNewAdmin(CreateUserRequest request);

}
