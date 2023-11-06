package fontys.sem3.school.business.Request.User;

import lombok.Data;
@Data
public class UpdateUserRequest {
    private Long id;
    private String name;
    private String username;
    private String email;
    private String password;
}
