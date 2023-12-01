package fontys.sem3.school.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.Set;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor

public class User {
private Long Id;
private String Name;
private String Username;
private String Email;
private String Password;
private Set<Role> roles;


}

