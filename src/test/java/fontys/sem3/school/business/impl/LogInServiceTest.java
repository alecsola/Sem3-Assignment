package fontys.sem3.school.business.impl;

import fontys.sem3.school.business.Request.LoginRequest;
import fontys.sem3.school.business.Response.User.LoginResponse;
import fontys.sem3.school.business.interfaces.User.ILoginService;
import fontys.sem3.school.business.interfaces.User.IUserService;
import fontys.sem3.school.domain.Theatre;
import fontys.sem3.school.domain.User;
import fontys.sem3.school.persistence.AccessTokenEncoder;
import fontys.sem3.school.persistence.repository.UserRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.transaction.annotation.Transactional;

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
        LogInService service = mock(LogInService.class);
        LoginResponse mockedLoginResponse = LoginResponse.builder().accessToken("mockedAccessToken").build();

        Mockito.when(service.login(Mockito.any(LoginRequest.class))).thenReturn(mockedLoginResponse);
        ILoginService sut = new LogInService(repository,passwordEncoder,accessTokenEncoder);

        //act
        LoginRequest request = new LoginRequest();
        request.setUsername("solaalec");
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
