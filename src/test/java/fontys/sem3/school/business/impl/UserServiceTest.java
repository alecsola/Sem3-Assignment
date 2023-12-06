package fontys.sem3.school.business.impl;

import fontys.sem3.school.business.Request.User.CreateUserRequest;
import fontys.sem3.school.domain.User;
import fontys.sem3.school.persistence.AccessTokenEncoder;
import fontys.sem3.school.persistence.repository.UserRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.stubbing.OngoingStubbing;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.multipart.MultipartFile;


import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;

public class UserServiceTest {
    /**
     * @verifies save a user and return id
     * @see UserService#saveNewUser(fontys.sem3.school.business.Request.User.CreateUserRequest)
     */
    @Test
    public void saveNewUser_shouldSaveAUserAndReturnId() throws Exception {
// Arrange
        UserRepository repository = mock(UserRepository.class);
        PasswordEncoder passwordEncoder = mock(PasswordEncoder.class);
        AccessTokenEncoder accessTokenEncoder = mock(AccessTokenEncoder.class);
        UserService userService = mock(UserService.class);
        CreateUserRequest createUserRequest = new CreateUserRequest("Alec","TestTest","Test@gmail.com","123456");

        Mockito.when(passwordEncoder.encode(anyString())).thenReturn("encodedPassword");
        Mockito.when(repository.saveNewUser(Mockito.any(User.class))).thenReturn(1L);

        // Act
        Long result = userService.saveNewUser(createUserRequest);

        // Assert
        assertEquals(1L, result);
        Mockito.verify(repository, Mockito.times(1)).saveNewUser(Mockito.any(User.class));

        Mockito.verify(passwordEncoder, times(1)).encode(anyString());
    }




    /**
     * @verifies return an error when it cant be created
     * @see UserService#saveNewUser(fontys.sem3.school.business.Request.User.CreateUserRequest)
     */
    @Test
    public void saveNewUser_shouldReturnAnErrorWhenItCantBeCreated() throws Exception {

    }

    /**
     * @verifies get a user by id
     * @see UserService#getUser(long)
     */
    @Test
    public void getUser_shouldGetAUserById() throws Exception {

    }

    /**
     * @verifies return an error user is not found
     * @see UserService#getUser(long)
     */
    @Test
    public void getUser_shouldReturnAnErrorUserIsNotFound() throws Exception {

    }
}
