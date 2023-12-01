package fontys.sem3.school.persistence;

public interface  AccessTokenEncoder {
    String encode(AccessToken accessToken);
}
