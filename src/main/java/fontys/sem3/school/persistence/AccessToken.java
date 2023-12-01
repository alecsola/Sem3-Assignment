package fontys.sem3.school.persistence;

import java.util.Set;

public interface AccessToken {
    String getName();

    Long getUserId();

    Set<String> getRoles();

    boolean hasRole(String roleName);
}
