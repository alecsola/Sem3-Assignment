package fontys.sem3.school.business.interfaces.User;

import fontys.sem3.school.business.Request.LoginRequest;
import fontys.sem3.school.business.Response.User.LoginResponse;

public interface ILoginService {
    boolean matchesPassword(String rawPassword, String encodedPassword);
    LoginResponse login(LoginRequest loginRequest);
}
