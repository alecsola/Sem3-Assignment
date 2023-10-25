package fontys.sem3.school.business.impl;

import fontys.sem3.school.business.Response.GetUserResponse;
import fontys.sem3.school.business.interfaces.UserUseCase;
import fontys.sem3.school.domain.User;
import fontys.sem3.school.persistence.repository.UserRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class UserUseCaseImplTest {



    /**
     * @verifies Get all Users successfully
     * @see UserUseCaseImpl#GetUser()
     */
    @Test
    public void GetUser_shouldGetAllUsersSuccessfully() throws Exception {
        //Arrange
        UserRepository userRepository = mock(UserRepository.class);
        List<User> testUsers = Arrays.asList(new User(1L,"Alec","sola.alec@gmail.com","solaalec","12345"));
        when(userRepository.GetUser()).thenReturn(testUsers);
        UserUseCase sut = new UserUseCaseImpl(userRepository);

        //Act
        //
        List<User> response = sut.GetUser();
        //Assert

        Assertions.assertEquals(testUsers, response);
    }

    /**
     * @verifies return an empty list
     * @see UserUseCaseImpl#GetUser()
     */
    @Test
    public void GetUser_shouldReturnAnEmptyList() throws Exception {
        //Arrange
        UserRepository userRepository = mock(UserRepository.class);
        List<User> testUsers = Arrays.asList();
        when(userRepository.GetUser()).thenReturn(testUsers);
        UserUseCase sut = new UserUseCaseImpl(userRepository);
        //Act
        List<User> response = sut.GetUser();
        //Assert
        assertThat(response.isEmpty());
    }

    /**
     * @verifies update a user
     * @see UserUseCaseImpl#updateUser(Long, String, String, String, String)
     */
    @Test
    public void updateUser_shouldUpdateAUser() throws Exception {
        //Arrange
        UserRepository userRepository = mock(UserRepository.class);
        Long Id = 1L;
        String Name = "Alec";
        String Email = "sola.alec@gmail.com";
        String Username = "solaaalec";
        String Password = "123";
        UserUseCase sut = new UserUseCaseImpl(userRepository);
        //Act
        User sutResponse = sut.updateUser(Id,Name,Email,Username,Password);
        //Assert
        assertNotNull(sutResponse.getUserId());
    }

    /**
     * @verifies return an exeption if user doesn't exist
     * @see UserUseCaseImpl#updateUser(Long, String, String, String, String)
     */
    @Test
    public void updateUser_shouldReturnAnExeptionIfUserDoesntExist() throws Exception {
        //TODO auto-generated
        Assertions.fail("Not yet implemented");
    }


   
}
