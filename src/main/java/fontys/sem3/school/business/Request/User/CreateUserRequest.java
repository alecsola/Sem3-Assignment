package fontys.sem3.school.business.Request.User;

import jakarta.validation.constraints.NotBlank;
import lombok.*;
import org.hibernate.validator.constraints.Length;

@Data
public class CreateUserRequest {

    private String name;

    private String username;

    private String email;

    private String password;
}
