package fontys.sem3.school.domain;

import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
public class CreateUserResponse {
    private Long UserId;
}
