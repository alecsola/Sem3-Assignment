package fontys.sem3.school.business.impl;

import fontys.sem3.school.business.Request.LoginRequest;
import fontys.sem3.school.business.Response.User.LoginResponse;
import fontys.sem3.school.business.interfaces.User.ILoginService;
import fontys.sem3.school.domain.Role;
import fontys.sem3.school.domain.User;
import fontys.sem3.school.persistence.AccessTokenEncoder;
import fontys.sem3.school.persistence.JPAmappers.RolesEnum;
import fontys.sem3.school.persistence.repository.UserRepository;
import jakarta.transaction.Transactional;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.HashSet;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.mock;

public class LogInServiceTest {
    /**
     * @verifies return access token
     * @see LogInService#login(fontys.sem3.school.business.Request.LoginRequest)
     */

    @Test
    @Transactional
    public void login_shouldReturnAccessToken() throws Exception {
        UserRepository repository = mock(UserRepository.class);
        PasswordEncoder passwordEncoder = mock(PasswordEncoder.class);
        AccessTokenEncoder accessTokenEncoder = mock(AccessTokenEncoder.class);
        User user = new User();
        Role role = new Role();
        role.setId(1L);
        role.setType(RolesEnum.ADMIN); // assuming RolesEnum.ADMIN is a valid value
        role.setUser(user);
        Set<Role> roles = new HashSet<>();
        roles.add(role);
        User mockUser = new User(1L,"Alec","solaalec1","sola.alec@gmail.com","123456",roles);
        Mockito.when(repository.findUserbyUsername("solaalec1")).thenReturn(mockUser);

        // Create the actual LogInService instance with the mocked repository
        ILoginService sut = new LogInService(repository, passwordEncoder, accessTokenEncoder);

        //act
        LoginRequest request = new LoginRequest();
        request.setUsername("solaalec1");
        request.setPassword("123456");
        LoginResponse sutResponse = sut.login(request);

        //arrange
        assertEquals("mockedAccessToken", sutResponse.getAccessToken());


    }

    /**
     * @verifies return an error when not returning access token
     * @see LogInService#login(fontys.sem3.school.business.Request.LoginRequest)
     */
    @Test
    public void login_shouldReturnAnErrorWhenNotReturningAccessToken() throws Exception {
        //TODO auto-generated
        Assertions.fail("Not yet implemented");
    }
}
