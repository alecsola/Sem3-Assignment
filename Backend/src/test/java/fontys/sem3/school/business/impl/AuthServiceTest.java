package fontys.sem3.school.business.impl;

import fontys.sem3.school.business.servicesInterfaces.IAuthService;
import fontys.sem3.school.business.servicesInterfaces.IEventService;
import fontys.sem3.school.persistence.AccessToken;
import fontys.sem3.school.persistence.config.AccessTokenEncoderDecoder;
import fontys.sem3.school.persistence.config.AccessTokenImpl;
import fontys.sem3.school.persistence.repository.EventRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class AuthServiceTest {

    @Test
    public void testExtractRolesFromToken() {
        // Arrange
        AccessTokenEncoderDecoder tokenEncoderDecoder= mock(AccessTokenEncoderDecoder.class);

        String encodedToken = "encodedToken";
        Set<String> roles = new HashSet<>(Collections.singletonList("role1"));
        AccessToken accessToken = new AccessTokenImpl("tokenName", 1L, roles);
        when(tokenEncoderDecoder.decode(encodedToken)).thenReturn(accessToken);

        // Act
        IAuthService sut = new AuthService(tokenEncoderDecoder);

        Set<String> extractedRoles = sut.extractRolesFromToken(encodedToken);

        // Assert
        assertEquals(roles, extractedRoles);
    }

    @Test
    public void testExtractUserIdFromToken() {
        // Arrange
        AccessTokenEncoderDecoder tokenEncoderDecoder= mock(AccessTokenEncoderDecoder.class);
        String encodedToken = "encodedToken";
        Long userId = 1L;
        AccessToken accessToken = new AccessTokenImpl("tokenName", userId, Collections.emptySet());
        when(tokenEncoderDecoder.decode(encodedToken)).thenReturn(accessToken);

        // Act
        IAuthService sut = new AuthService(tokenEncoderDecoder);
        Long extractedUserId = sut.extractUserIdFromToken(encodedToken);

        // Assert
        assertEquals(userId, extractedUserId);
    }
}
