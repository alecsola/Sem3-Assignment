package fontys.sem3.school.business.impl;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.bcrypt.BCrypt;
import fontys.sem3.school.domain.User;
import fontys.sem3.school.persistence.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class AuthenticationService {

    private final UserRepository userRepository;

    public User validateUserCredentials(String username, String password) {
        try {
            List<User> users = userRepository.GetUser();
            for (User user : users) {
                if (user.getUsername().equals(username)) {
                    if (BCrypt.checkpw(password, user.getHashedPassword())) {
                        return user;
                    } else {
                        throw new IllegalArgumentException("Password is incorrect");
                    }
                }
            }
            throw new IllegalArgumentException("Username not found");

        } catch (Exception e) {
            throw new IllegalArgumentException("Something went wrong");
        }
    }

}
