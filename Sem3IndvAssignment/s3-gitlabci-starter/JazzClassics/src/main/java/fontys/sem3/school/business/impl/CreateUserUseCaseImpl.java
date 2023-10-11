package fontys.sem3.school.business.impl;

import fontys.sem3.school.business.interfaces.IUserRepository;
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
        if (user.getPassword().length() < 8 && user.getPassword().length() >3){
            User newuser = User.builder()

                    .Name(user.getName())
                    .Username(user.getUsername())
                    .Email(user.getEmail())
                    .Password(user.getPassword())
                    .build();

            return userRepository.saveUser(newuser);
        }
        else{

            throw new IllegalArgumentException("Password cannot less than 3 characters or more than 8");
        }


    }
}
