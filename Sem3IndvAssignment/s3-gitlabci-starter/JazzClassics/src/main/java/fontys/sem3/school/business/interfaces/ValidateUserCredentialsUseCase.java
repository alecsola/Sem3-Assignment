package fontys.sem3.school.business.interfaces;

import fontys.sem3.school.domain.User;

public interface ValidateUserCredentialsUseCase {

    User ValidateUserCredentials(String username, String password);
}
