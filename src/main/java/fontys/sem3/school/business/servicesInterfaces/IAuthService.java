package fontys.sem3.school.business.servicesInterfaces;

import java.util.Set;

public interface IAuthService {
    Set<String> extractRolesFromToken(String accessTokenEncoded);
    long extractUserIdFromToken(String accessTokenEncoded);
}
