package fontys.sem3.school.business.impl;

import fontys.sem3.school.business.interfaces.UpdateUserUseCase;
import fontys.sem3.school.domain.User;
import fontys.sem3.school.persistence.repository.UserRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.mock;

public class UpdateUserUseCaseImplTest {
    /**
     * @verifies update a user
     * @see UpdateUserUseCaseImpl#updateUser(int, String, String, String)
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
        UpdateUserUseCase sut = new UpdateUserUseCaseImpl(userRepository);
        //Act
        User sutResponse = sut.updateUser(Id,Name,Email,Username,Password);
        //Assert
        assertNotNull(sutResponse.getUserId());

    }

    /**
     * @verifies get an error if the user does not exist
     * @see UpdateUserUseCaseImpl#updateUser(int, String, String, String)
     */
    @Test
    public void updateUser_shouldGetAnErrorIfTheUserDoesNotExist() throws Exception {

        Assertions.fail("Not yet implemented");
    }
}
