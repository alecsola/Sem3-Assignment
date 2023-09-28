package fontys.sem3.school.persistence.repository;

import fontys.sem3.school.business.interfaces.IUserRepository;
import fontys.sem3.school.domain.User;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

@Repository
public class UserRepository implements IUserRepository {

private final List<User> users;
private User user;

    public UserRepository(List<User> users) {
        this.users = new ArrayList<>();
    }

    @Override
    public User findbyId(Long id){
        User user = new User();
        if(user.getUserId() == id){
            return user;
        }
        return null;
    }


    @Override
    public List<User> GetUser() {
        List<User> testUsers = Arrays.asList(new User(1L,"Alec","sola.alec@gmail.com","12345"));
        return Collections.unmodifiableList(testUsers);
    }


    public User saveUser(User newuser){
        // List<User> users = new ArrayList<>();
        users.add(newuser);
        return newuser;
    }


}
