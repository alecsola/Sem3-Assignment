package fontys.sem3.school.controller.Converter;

import fontys.sem3.school.controller.Request.CreateUserRequest;
import fontys.sem3.school.controller.Response.CreateUserResponse;
import fontys.sem3.school.domain.User;
import lombok.Data;
import org.springframework.stereotype.Component;


@Component
public class Converter {

    public User userRequestConverter(CreateUserRequest request){
        User user = new User();
        user.setUserId(1L);
        user.setName(request.getName());
        user.setEmail(request.getEmail());
        user.setPassword(request.getPassword());

        return user;

    }
    public CreateUserResponse responseConverter(User user){
        CreateUserResponse response = new CreateUserResponse();
        response.setUserId(user.getUserId());
        return response;
    }
}
