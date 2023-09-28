package fontys.sem3.school.business.impl;

import fontys.sem3.school.business.interfaces.CreateUserUseCase;
import fontys.sem3.school.controller.Request.CreateUserRequest;
import fontys.sem3.school.controller.Response.CreateUserResponse;
import fontys.sem3.school.domain.User;
import fontys.sem3.school.persistence.repository.UserRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatExceptionOfType;
import static org.junit.jupiter.api.Assertions.*;
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
        User user = new User(1L,"John Doe", "john@example.com", "123456");
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

        //Arrange
        UserRepository userRepository =mock(UserRepository.class);
        User user = new User(1L,"John Doe", "john@example.com", "12");
        CreateUserUseCase sut = new CreateUserUseCaseImpl(userRepository);
        // Act
        when(userRepository.saveUser(any())).thenThrow(new IllegalArgumentException("Password cannot be less than 3 characters or more than 8"));

        // Assert
        assertThrows(IllegalArgumentException.class, () -> {
            sut.createUser(user);
        });

    }
}
