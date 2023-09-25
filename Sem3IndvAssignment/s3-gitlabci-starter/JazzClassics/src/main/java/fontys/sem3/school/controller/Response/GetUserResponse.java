package fontys.sem3.school.controller.Response;

import fontys.sem3.school.domain.User;
import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@Builder
public class GetUserResponse  {
    private List<User> users;

    public boolean IsSuccess(){
        return true;
    }
}
