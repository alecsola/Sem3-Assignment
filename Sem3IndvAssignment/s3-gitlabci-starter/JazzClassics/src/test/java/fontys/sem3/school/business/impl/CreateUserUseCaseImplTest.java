package fontys.sem3.school.business.impl;

import fontys.sem3.school.business.interfaces.CreateUserUseCase;
import fontys.sem3.school.controller.Request.CreateUserRequest;
import fontys.sem3.school.controller.Response.CreateUserResponse;
import fontys.sem3.school.domain.User;
import fontys.sem3.school.persistence.repository.UserRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class CreateUserUseCaseImplTest {
    private CreateUserUseCaseImpl createUserUseCase;
    /**
     * @verifies create a user successfully
     * @see
     */
    @Test
    public void createUser_shouldCreateAUserSuccessfully() throws Exception {
        //TODO auto-generated
        //Arrange
        UserRepository userRepository =mock(UserRepository.class);
        User user = new User(1L,"John Doe", "john@example.com", "password");
        when(userRepository.saveUser(any())).thenReturn(user);
        CreateUserUseCase sut = new CreateUserUseCaseImpl(userRepository);
        // Act
        User sutResponse = sut.createUser(user);

        // Assert
        assertNotNull(sutResponse.getUserId());
     }

    /**
     * @verifies not create a user when the password has only 2 letters.
     * @see
     */
    @Test
    public void createUser_shouldNotCreateAUserWhenThePasswordHasOnly2Letters() throws Exception {
        //TODO auto-generated
        Assertions.fail("Not yet implemented");
    }
}
