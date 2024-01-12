package fontys.sem3.school.business.Request.User;

import fontys.sem3.school.domain.Role;
import fontys.sem3.school.domain.User;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Set;

@Data
@AllArgsConstructor
@Builder
@NoArgsConstructor
public class UpdateUserRequest {
    User user;
}
