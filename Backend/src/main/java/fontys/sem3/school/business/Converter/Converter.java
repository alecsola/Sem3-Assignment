package fontys.sem3.school.business.Converter;

import fontys.sem3.school.business.Request.User.CreateUserRequest;
import fontys.sem3.school.business.Request.User.UpdateUserRequest;
import fontys.sem3.school.business.Response.User.CreateUserResponse;
import fontys.sem3.school.business.Response.User.GetUserResponse;
import fontys.sem3.school.domain.User;
import org.springframework.stereotype.Component;


@Component
public class Converter {

    public static User userRequestConverter(CreateUserRequest request){
        User user = new User();
        user.setName(request.getName());
        user.setUsername(request.getUsername());
        user.setEmail(request.getEmail());
        user.setPassword(request.getPassword());
        return user;

    }
    public static GetUserResponse userRequestConverter(User user){
        GetUserResponse userResponse = new GetUserResponse();
        userResponse.setUser(user);
        return userResponse;

    }
    public static CreateUserResponse responseConverter(User user){
        CreateUserResponse response = new CreateUserResponse();
        response.setId(user.getId());
        return response;
    }
    public static UpdateUserRequest updateUserRequestConv(User user){
        User newUser = new User(user.getId(), user.getName(), user.getUsername(), user.getEmail(), user.getPassword(), user.getRoles());
        return new UpdateUserRequest(newUser);
    }


}
