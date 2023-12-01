package fontys.sem3.school.business.Response.User;

import fontys.sem3.school.domain.User;
import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@Builder
public class GetUsersResponse {
    private List<User> users;


}
