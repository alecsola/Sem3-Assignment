package fontys.sem3.school.business.impl;

import fontys.sem3.school.business.servicesInterfaces.IAuthService;
import fontys.sem3.school.persistence.AccessToken;
import fontys.sem3.school.persistence.config.AccessTokenEncoderDecoder;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Set;

@Service
@RequiredArgsConstructor
public class AuthService implements IAuthService {

    private final AccessTokenEncoderDecoder tokenEncoderDecoder;

    public Set<String> extractRolesFromToken(String accessTokenEncoded) {
        AccessToken decodedToken = tokenEncoderDecoder.decode(accessTokenEncoded);
        return decodedToken.getRoles();
    }
    public long extractUserIdFromToken(String accessTokenEncoded) {
        AccessToken decodedToken = tokenEncoderDecoder.decode(accessTokenEncoded);
        return decodedToken.getUserId();
    }
}
