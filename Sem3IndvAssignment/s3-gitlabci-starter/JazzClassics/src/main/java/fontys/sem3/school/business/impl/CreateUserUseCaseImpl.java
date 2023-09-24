package fontys.sem3.school.business.impl;

import fontys.sem3.school.business.IUserRepository;
import fontys.sem3.school.business.interfaces.CreateUserUseCase;
import fontys.sem3.school.domain.CreateUserRequest;
import fontys.sem3.school.domain.CreateUserResponse;
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
    public CreateUserResponse createUser(CreateUserRequest request) {

        User newUser = SaveUser(request);
        return CreateUserResponse.builder()
                .UserId(newUser.getUserId()).build();

    }
    public User SaveUser(CreateUserRequest request){
        User user = User.builder()
                .UserId(1L)
                .Name(request.getName())
                .Email(request.getEmail())
                .Password(request.getPassword())
                .build();
        return userRepository.saveUser(user);

    }
}
