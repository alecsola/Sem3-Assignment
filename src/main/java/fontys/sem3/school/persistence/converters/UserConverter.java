package fontys.sem3.school.persistence.converters;

import fontys.sem3.school.business.Request.User.CreateUserRequest;
import fontys.sem3.school.domain.Role;
import fontys.sem3.school.domain.User;
import fontys.sem3.school.persistence.JPAmappers.RoleJPAmapper;
import fontys.sem3.school.persistence.JPAmappers.RolesEnum;
import fontys.sem3.school.persistence.JPAmappers.UserJPAmapper;

import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

public class UserConverter {

    public static UserJPAmapper userConverter(User user){

        UserJPAmapper newUser = UserJPAmapper.builder()
                .name(user.getName())
                .username(user.getUsername())
                .email(user.getEmail())
                .password(user.getPassword())
                .build();
        newUser.setRoles(Set.of(
                RoleJPAmapper.builder()
                        .user(newUser)
                        .type(RolesEnum.CUSTOMER)
                        .build()));

        return newUser;

    }
    public static User userJPAmapperConverter(UserJPAmapper userJPAmapper) {
        User newUser = new User();
        newUser.setId(userJPAmapper.getId());
        newUser.setName(userJPAmapper.getName());
        newUser.setUsername(userJPAmapper.getUsername());
        newUser.setEmail(userJPAmapper.getEmail());
        newUser.setPassword(userJPAmapper.getPassword());

        Set<Role> roles = new HashSet<>();
        for (RoleJPAmapper roleJPAmapper : userJPAmapper.getRoles()) {
            roles.add(roleJPAmapperConverter(roleJPAmapper));
        }
        newUser.setRoles(roles);

        return newUser;
    }
    public static Role roleJPAmapperConverter(RoleJPAmapper roleJPAmapper) {
        Role newRole = new Role();
        newRole.setId(roleJPAmapper.getId());
        newRole.setType(roleJPAmapper.getType());
        newRole.setUser(null); // or set it to a default User object
        return newRole;
    }
}
