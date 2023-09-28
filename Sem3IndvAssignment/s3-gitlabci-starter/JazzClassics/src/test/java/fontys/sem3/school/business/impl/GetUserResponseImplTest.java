package fontys.sem3.school.business.impl;

import fontys.sem3.school.business.interfaces.GetUserUseCase;
import fontys.sem3.school.controller.Response.GetUserResponse;
import fontys.sem3.school.domain.User;
import fontys.sem3.school.persistence.repository.UserRepository;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class GetUserResponseImplTest {
    /**
     * @verifies get all users.
     * @see GetUserResponseImpl#GetUser()
     */
    @Test
    public void GetUser_shouldGetAllUsers() throws Exception {
        //TODO auto-generated
        //Arrange
        UserRepository userRepository = mock(UserRepository.class);
        List<User> testUsers = Arrays.asList(new User(1L,"Alec","sola.alec@gmail.com","12345"));
        when(userRepository.GetUser()).thenReturn(testUsers);
        GetUserUseCase sut = new GetUserResponseImpl(userRepository);

        //Act
        //
         GetUserResponse response = sut.GetUser();
        //Assert

        assertTrue(response.IsSuccess());
    }

    /**
     * @verifies get empty list if no users
     * @see GetUserResponseImpl#GetUser()
     */
    @Test
    public void GetUser_shouldGetEmptyListIfNoUsers() throws Exception {
        //TODO auto-generated
        //Arrange
        UserRepository userRepository = mock(UserRepository.class);
        List<User> testUsers = Arrays.asList();
        when(userRepository.GetUser()).thenReturn(testUsers);
        GetUserUseCase sut = new GetUserResponseImpl(userRepository);
        //Act
        GetUserResponse response = sut.GetUser();
        //Assert
        assertThat(response.getUsers().isEmpty());
    }
}
