package fontys.sem3.school.business.impl;

import fontys.sem3.school.business.IUserRepository;
import fontys.sem3.school.business.interfaces.GetUserUseCase;
import fontys.sem3.school.domain.GetUserResponse;
import fontys.sem3.school.domain.User;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class GetUserResponseImpl implements GetUserUseCase {

    private final IUserRepository userRepository;

    /**
     * @should get all users.
     * @should get empty list if no users
     */
    public GetUserResponse GetUser() {
        //TODO
        List<User> users = userRepository.GetUser()
                .stream()
                .toList();

        return GetUserResponse.builder()
                .users(users)
                .build();
    }
}
