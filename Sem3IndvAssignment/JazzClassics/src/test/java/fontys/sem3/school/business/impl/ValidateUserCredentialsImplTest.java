package fontys.sem3.school.business.impl;

import fontys.sem3.school.business.interfaces.CreateUserUseCase;
import fontys.sem3.school.business.interfaces.ValidateUserCredentialsUseCase;
import fontys.sem3.school.domain.User;
import fontys.sem3.school.persistence.repository.UserRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class ValidateUserCredentialsImplTest {
    /**
     * @verifies validate user credentials
     * @see ValidateUserCredentialsImpl#ValidateUserCredentials(String, String)
     */


    @Test
    public void ValidateUserCredentials_shouldValidateUserCredentials() throws Exception {
        //TODO auto-generated
        //Arrange]
        UserRepository userRepository = mock(UserRepository.class);
        List<User> testUsers = Arrays.asList(new User(1L,"Alec","sola.alec@gmail.com","solaalec","12345"));
        when(userRepository.GetUser()).thenReturn(testUsers);
       ValidateUserCredentialsUseCase sut = new ValidateUserCredentialsImpl(userRepository);
        //Act
        User retrievedUser = sut.ValidateUserCredentials("solaalec", "12345");
        //Assert
        assertNotNull(retrievedUser);
    }

    /**
     * @verifies throw an exception if the validation is incorrect
     * @see ValidateUserCredentialsImpl#ValidateUserCredentials(String, String)
     */
    @Test
    public void ValidateUserCredentials_shouldThrowAnExceptionIfTheValidationIsIncorrect() throws Exception {
        //TODO auto-generated
        Assertions.fail("Not yet implemented");
    }
}
