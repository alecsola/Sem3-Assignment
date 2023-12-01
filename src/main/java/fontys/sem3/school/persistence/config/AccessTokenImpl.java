package fontys.sem3.school.persistence.config;

import fontys.sem3.school.persistence.AccessToken;
import lombok.EqualsAndHashCode;
import lombok.Getter;

import java.util.Collection;
import java.util.Collections;
import java.util.Set;

@EqualsAndHashCode
@Getter
public class AccessTokenImpl implements AccessToken {
    private final String name;
    private final Long userId;
    private final Set<String> roles;

    public AccessTokenImpl(String name, Long userId, Collection<String> roles) {
        this.name = name;
        this.userId = userId;
        this.roles = roles != null ? Set.copyOf(roles) : Collections.emptySet();
    }


    public boolean hasRole(String roleName) {
        return this.roles.contains(roleName);
    }
}
