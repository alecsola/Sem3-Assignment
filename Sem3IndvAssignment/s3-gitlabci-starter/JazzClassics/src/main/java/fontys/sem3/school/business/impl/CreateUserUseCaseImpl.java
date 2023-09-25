package fontys.sem3.school.business.impl;

import fontys.sem3.school.business.IUserRepository;
import fontys.sem3.school.business.interfaces.CreateUserUseCase;
import fontys.sem3.school.domain.User;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;


@Service
@RequiredArgsConstructor
public class CreateUserUseCaseImpl implements CreateUserUseCase {
    private final IUserRepository userRepository;

    /**
     *
     *
     * @should create a user successfully
     * @should not create a user when the password has only 2 letters.
     */
    public User createUser(User user) {

        User newUser = SaveUser(user);
        return User.builder()
                .UserId(newUser.getUserId()).build();

    }
    public User SaveUser(User user){
        User newuser = User.builder()
                .UserId(1L)
                .Name(user.getName())
                .Email(user.getEmail())
                .Password(user.getPassword())
                .build();
        return userRepository.saveUser(newuser);

    }
}
