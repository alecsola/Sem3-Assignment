package fontys.sem3.school.business.impl;

import fontys.sem3.school.business.Response.GetUserResponse;
import fontys.sem3.school.business.interfaces.IUserRepository;
import fontys.sem3.school.business.interfaces.UserUseCase;
import fontys.sem3.school.domain.User;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

@Service
@RequiredArgsConstructor
public class UserUseCaseImpl implements UserUseCase {


    private final IUserRepository userRepository;



    public String hashPassword(String password) {
        String salt = BCrypt.gensalt();
        return BCrypt.hashpw(password, salt);
    }

    public User createUser(User user) {
        String hashedPassword = hashPassword(user.getHashedPassword());
        List<User> users = userRepository.GetUser();

        for (User founduser : users) {
            if (founduser.getUsername().equals(user.getUsername())) {
                throw new IllegalArgumentException("Username already exists");
            }
        }

        User newUser = new User(user.getUserId(), user.getName(), user.getUsername(), user.getEmail(), hashedPassword);
        userRepository.saveUser(newUser);

        return newUser; // return the created user
    }



    public boolean deleteUser(User user){
        List<User> users = userRepository.GetUser();
        if(users.contains(user)){
            users.remove(user);
            return true;
        }
        return false;
    }


    /**
     * @should Get all Users successfully
     * @should return an empty list
     */
    public List<User> GetUser() {
        List<User> users = userRepository.GetUser();
        return Collections.unmodifiableList(users);
    }


    public User getUserbyId(Long id){
        return userRepository.getUserbyId(id);
    }

    /**
     * @should update a user
     * @should return an exeption if user doesn't exist
     */
    public User updateUser(Long Id, String Name, String Username,String Email, String Password){
        User user = userRepository.getUserbyId(Id);
        if(user != null){
            String hashedPassword = hashPassword(Password);
            user.setName(Name);
            user.setUsername(Username);
            user.setEmail(Email);

            return user;
        }
        else{
            throw new IllegalArgumentException("User not found with ID: " + Id);
        }

    }



}
