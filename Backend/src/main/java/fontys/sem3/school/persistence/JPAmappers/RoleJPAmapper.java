package fontys.sem3.school.persistence.JPAmappers;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "role")
@Setter
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class RoleJPAmapper {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Enumerated(EnumType.STRING)
    private RolesEnum type;
    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private UserJPAmapper user;
}
