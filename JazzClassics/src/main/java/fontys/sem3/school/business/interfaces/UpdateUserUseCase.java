package fontys.sem3.school.business.interfaces;

import fontys.sem3.school.domain.User;

public interface UpdateUserUseCase {
    User updateUser(Long Id, String Name,String Email, String Username, String Password);
}
