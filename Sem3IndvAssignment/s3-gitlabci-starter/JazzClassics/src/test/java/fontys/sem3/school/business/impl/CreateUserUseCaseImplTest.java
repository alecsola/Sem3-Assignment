package fontys.sem3.school.business.impl;

import fontys.sem3.school.business.interfaces.CreateUserUseCase;
import fontys.sem3.school.domain.CreateUserRequest;
import fontys.sem3.school.domain.CreateUserResponse;
import fontys.sem3.school.domain.User;
import fontys.sem3.school.persistence.repository.UserRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.mock;

public class CreateUserUseCaseImplTest {
    private CreateUserUseCaseImpl createUserUseCase;
    /**
     * @verifies create a user successfully
     * @see CreateUserUseCaseImpl#createUser(fontys.sem3.school.domain.CreateUserRequest)
     */
    @Test
    public void createUser_shouldCreateAUserSuccessfully() throws Exception {
        //TODO auto-generated
        //Arrange
        UserRepository userRepository =mock(UserRepository.class);
        CreateUserRequest request = new CreateUserRequest("John Doe", "john@example.com", "password");

        CreateUserUseCase sut = new CreateUserUseCaseImpl(userRepository);
        // Act
        CreateUserResponse sutResponse = sut.createUser(request);

        // Assert
        assertNotNull(sutResponse.getUserId());
    }

    /**
     * @verifies not create a user when the password has only 2 letters.
     * @see CreateUserUseCaseImpl#createUser(fontys.sem3.school.domain.CreateUserRequest)
     */
    @Test
    public void createUser_shouldNotCreateAUserWhenThePasswordHasOnly2Letters() throws Exception {
        //TODO auto-generated
        Assertions.fail("Not yet implemented");
    }
}
