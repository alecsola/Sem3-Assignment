package fontys.sem3.school.business.Converter;

import fontys.sem3.school.business.Request.User.CreateUserRequest;
import fontys.sem3.school.business.Response.User.CreateUserResponse;
import fontys.sem3.school.domain.User;
import org.springframework.stereotype.Component;


@Component
public class Converter {

    public User userRequestConverter(CreateUserRequest request){
        User user = new User();

        user.setName(request.getName());
        user.setUsername(request.getUsername());
        user.setEmail(request.getEmail());
        user.setHashedPassword(request.getPassword());

        return user;

    }
    public CreateUserResponse responseConverter(User user){
        CreateUserResponse response = new CreateUserResponse();
        response.setUserId(user.getId());
        return response;
    }
}
