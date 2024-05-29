package fontys.sem3.school.domain;

import fontys.sem3.school.persistence.JPAmappers.RolesEnum;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor

public class Role {

    private Long Id;
    private RolesEnum Type;
    private User user;
    @Override
    public String toString() {
        return "Role{" +
                "id=" + Id +
                ", type=" + Type +
                '}';
    }
}
