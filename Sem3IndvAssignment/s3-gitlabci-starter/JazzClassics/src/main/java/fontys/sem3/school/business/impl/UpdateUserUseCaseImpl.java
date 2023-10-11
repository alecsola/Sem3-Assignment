package fontys.sem3.school.business.impl;
import fontys.sem3.school.business.interfaces.IUserRepository;
import fontys.sem3.school.business.interfaces.UpdateUserUseCase;
import fontys.sem3.school.domain.User;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;

@Service
@RequiredArgsConstructor
public class UpdateUserUseCaseImpl implements UpdateUserUseCase {

    private final IUserRepository userRepository;

    /**
     * @should update a user
     * @should get an error if the user does not exist
     */
    public User updateUser(Long Id, String Name, String Username,String Email, String Password){
        List<User> users = Arrays.asList(new User(1L,"Alec","sola.alec@gmail.com","solaalec","12345"), new User(2L,"Max","maxense.sola@gmail.com","max","123"));// Assuming getUsers() returns a list of users

        for (User user : users) {
            if (user.getUserId().equals(Id)) {
                // Found the correct user, update the information
                user.setName(Name);
                user.setUsername(Username);
                user.setEmail(Email);
                user.setPassword(Password);

                // Return the updated user
                return user;
            }
        }

        // If the user with the specified ID is not found, you may want to handle this case accordingly
        return null;
    }

}
