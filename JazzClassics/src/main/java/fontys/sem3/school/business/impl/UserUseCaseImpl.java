package fontys.sem3.school.business.impl;

import fontys.sem3.school.business.Response.GetUserResponse;
import fontys.sem3.school.business.interfaces.IUserRepository;
import fontys.sem3.school.business.interfaces.UserUseCase;
import fontys.sem3.school.domain.User;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;

@Service
@RequiredArgsConstructor
public class UserUseCaseImpl implements UserUseCase {


    private final IUserRepository userRepository;

    /**
     * @should create a user successfully
     * @should not create a user when the password has only 2 letters.
     */

    public User createUser(User user) {

        User newUser = SaveUser(user);
        return User.builder()
                .UserId(newUser.getUserId()).build();

    }
    public boolean deleteUser(User user){
        List<User> users = userRepository.GetUser();
        if(users.contains(user)){
            users.remove(user);
            return true;
        }
        return false;
    }


    public User SaveUser(User user){
        if (user.getPassword().length() < 8 && user.getPassword().length() >3){
            User newuser = User.builder()

                    .Name(user.getName())
                    .Username(user.getUsername())
                    .Email(user.getEmail())
                    .Password(user.getPassword())
                    .build();

            return userRepository.saveUser(newuser);
        }
        else{

            throw new IllegalArgumentException("Password cannot less than 3 characters or more than 8");
        }


    }

    /**
     * @should Get all Users successfully
     * @should return an empty list
     */
    public GetUserResponse GetUser() {
        List<User> users = userRepository.GetUser()
                .stream()
                .toList();

        return GetUserResponse.builder()
                .users(users)
                .build();
    }

    /**
     * @should update a user
     * @should return an exeption if user doesn't exist
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

    /** @should validate User Credentials
     * @should give an error if they aren't validated
     */
    public User ValidateUserCredentials (String username, String password){
        try{

            List<User> users;
            users = userRepository.GetUser();
            for (User user : users) {
                if (user.getUsername().equals(username)) {
                    if (user.getPassword().equals(password)) {
                        return user;
                    } else {
                        throw new IllegalArgumentException("Password is incorrect");
                    }
                }
            }
            throw new IllegalArgumentException("The Username is not found");

        }
        catch(Exception e){
            throw new IllegalArgumentException("Something went wrong");
        }



    }
}
