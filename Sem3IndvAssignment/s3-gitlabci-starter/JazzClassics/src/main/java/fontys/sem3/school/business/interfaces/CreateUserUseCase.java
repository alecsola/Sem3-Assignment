package fontys.sem3.school.business.interfaces;

import fontys.sem3.school.domain.CreateUserRequest;
import fontys.sem3.school.domain.CreateUserResponse;

public interface CreateUserUseCase {
    CreateUserResponse createUser (CreateUserRequest request);
}
