package fontys.sem3.school.business.Request;

import lombok.Data;
import org.hibernate.validator.constraints.Length;

@Data
public class LoginRequest {

    private String username;
    @Length(min=3, max=10)
    private String password;
}
