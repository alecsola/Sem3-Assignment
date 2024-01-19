package fontys.sem3.school.business.impl;

import fontys.sem3.school.business.Request.User.CreateUserRequest;
import fontys.sem3.school.business.Response.User.GetUserResponse;
import fontys.sem3.school.business.interfaces.User.IUserRepositoryBusiness;
import fontys.sem3.school.domain.Role;
import fontys.sem3.school.domain.User;
import fontys.sem3.school.persistence.AccessTokenEncoder;
import fontys.sem3.school.persistence.JPAmappers.RolesEnum;
import fontys.sem3.school.persistence.repository.UserRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.stubbing.OngoingStubbing;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;


import java.util.Collections;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.*;

public class UserServiceTest {
    /**
     * @verifies save a user and return id
     * @see UserService#saveNewUser(fontys.sem3.school.business.Request.User.CreateUserRequest)
     */
    @Test
    @Transactional
    public void saveNewUser_shouldSaveAUserAndReturnId() throws Exception {
        // Arrange
        IUserRepositoryBusiness repository = mock(IUserRepositoryBusiness.class);
        UserRepository userRepository = mock(UserRepository.class);
        PasswordEncoder passwordEncoder = mock(PasswordEncoder.class);

        UserService userService = new UserService(repository);
        CreateUserRequest createUserRequest = new CreateUserRequest("Alec", "TestTest", "Test@gmail.com", "123456");
        when(passwordEncoder.encode(anyString())).thenReturn("encodedPassword");
        when(userRepository.saveNewUser(Mockito.any(User.class))).thenReturn(1L);
        when(repository.saveNewUser(Mockito.any(User.class))).thenReturn(1L);

        // Act
        Long result = userService.saveNewUser(createUserRequest);

        // Assert
        assertEquals(1L, result);

    }

    @Test
    public void saveNewAdmin() throws Exception {
        // Arrange
        IUserRepositoryBusiness repository = mock(IUserRepositoryBusiness.class);
        UserRepository userRepository = mock(UserRepository.class);
        PasswordEncoder passwordEncoder = mock(PasswordEncoder.class);

        UserService userService = new UserService(repository);
        CreateUserRequest createUserRequest = new CreateUserRequest("Alec", "TestTest", "Test@gmail.com", "123456");
        when(passwordEncoder.encode(anyString())).thenReturn("encodedPassword");
        when(userRepository.saveNewAdmin(Mockito.any(User.class))).thenReturn(1L);
        when(repository.saveNewAdmin(Mockito.any(User.class))).thenReturn(1L);

        // Act
        Long result = userService.saveNewAdmin(createUserRequest);

        // Assert
        assertEquals(1L, result);

    }
    @Test
    @Transactional
    public void saveNewAdmin_shouldReturnAnErrorWhenItCantBeCreated() throws Exception {
        // Arrange
        IUserRepositoryBusiness repository = mock(IUserRepositoryBusiness.class);
        UserRepository userRepository = mock(UserRepository.class);
        PasswordEncoder passwordEncoder = mock(PasswordEncoder.class);

        UserService userService = new UserService(repository);
        CreateUserRequest createUserRequest = new CreateUserRequest("Alec", "TestTest", "Test@gmail.com", "123");
        when(passwordEncoder.encode(anyString())).thenReturn("encodedPassword");
        when(userRepository.saveNewUser(Mockito.any(User.class))).thenReturn(1L);
        when(repository.saveNewUser(Mockito.any(User.class))).thenReturn(1L);


        // Act and Assert
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> {
            userService.saveNewAdmin(createUserRequest);
        });

        // Verify the exception message
        assertEquals("Password is too short", exception.getMessage());

    }




    /**
     * @verifies return an error when it cant be created
     * @see UserService#saveNewUser(fontys.sem3.school.business.Request.User.CreateUserRequest)
     */
    @Test
    @Transactional
    public void saveNewUser_shouldReturnAnErrorWhenItCantBeCreated() throws Exception {
        // Arrange
        IUserRepositoryBusiness repository = mock(IUserRepositoryBusiness.class);
        UserRepository userRepository = mock(UserRepository.class);
        PasswordEncoder passwordEncoder = mock(PasswordEncoder.class);

        UserService userService = new UserService(repository);
        CreateUserRequest createUserRequest = new CreateUserRequest("Alec", "TestTest", "Test@gmail.com", "123");
        when(passwordEncoder.encode(anyString())).thenReturn("encodedPassword");
        when(userRepository.saveNewUser(Mockito.any(User.class))).thenReturn(1L);
        when(repository.saveNewUser(Mockito.any(User.class))).thenReturn(1L);


        // Act and Assert
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> {
            userService.saveNewUser(createUserRequest);
        });

        // Verify the exception message
        assertEquals("Password is too short", exception.getMessage());

    }

   



}
