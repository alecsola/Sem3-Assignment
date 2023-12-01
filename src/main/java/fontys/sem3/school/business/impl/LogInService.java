package fontys.sem3.school.business.impl;

import fontys.sem3.school.business.Request.LoginRequest;
import fontys.sem3.school.business.Response.User.LoginResponse;
import fontys.sem3.school.business.interfaces.User.ILoginService;
import fontys.sem3.school.domain.Role;
import fontys.sem3.school.domain.User;
import fontys.sem3.school.persistence.AccessTokenEncoder;
import fontys.sem3.school.persistence.JPAmappers.UserJPAmapper;
import fontys.sem3.school.persistence.config.AccessTokenImpl;
import fontys.sem3.school.persistence.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class LogInService implements ILoginService {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final AccessTokenEncoder accessTokenEncoder;



    /**
     * @should return access token
     * @should return an error when not returning access token
     */
    @Override
    public LoginResponse login(LoginRequest loginRequest) {
        User user = userRepository.findUserbyUsername(loginRequest.getUsername());
        if (user == null) {
            throw new IllegalArgumentException("User does not exist");
        }

        if (!matchesPassword(loginRequest.getPassword(), user.getPassword())) {
            throw new IllegalArgumentException("Password is incorrect");
        }

        String accessToken = generateAccessToken(user);
        return LoginResponse.builder().accessToken(accessToken).build();
    }

    public boolean matchesPassword(String rawPassword, String encodedPassword) {
        return passwordEncoder.matches(rawPassword, encodedPassword);
    }



    private String generateAccessToken(User user) {
        Long userId = user.getId() != null ? user.getId() : null;
        List<String> roles = user.getRoles().stream()
                .map(role -> role.getType().name())  // Assuming RolesEnum has a method to get the name as String
                .collect(Collectors.toList());

        return accessTokenEncoder.encode(
                new AccessTokenImpl(user.getName(), userId, roles));
    }
}
