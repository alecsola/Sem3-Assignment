package fontys.sem3.school.business.impl;


import ch.qos.logback.classic.spi.IThrowableProxy;
import fontys.sem3.school.business.interfaces.IUserRepository;
import fontys.sem3.school.business.interfaces.ValidateUserCredentialsUseCase;
import fontys.sem3.school.domain.User;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ValidateUserCredentialsImpl implements ValidateUserCredentialsUseCase {

    private final IUserRepository userRepository;
    /**
     *
     *
     * @should validate user credentials
     * @should throw an exception if the validation is incorrect
     */

    //Supposed to validate User Credentials for the Login
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
