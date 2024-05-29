package fontys.sem3.school.persistence;

public interface AccessTokenDecoder {
    AccessToken decode(String accessTokenEncoded);
}
