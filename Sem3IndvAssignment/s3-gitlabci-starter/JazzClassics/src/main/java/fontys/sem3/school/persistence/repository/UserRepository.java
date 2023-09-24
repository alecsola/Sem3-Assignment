package fontys.sem3.school.persistence.repository;

import fontys.sem3.school.business.IUserRepository;
import fontys.sem3.school.domain.User;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Repository
public class UserRepository implements IUserRepository {

private final List<User> users;

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
        return Collections.unmodifiableList(this.users);
    }


    public User saveUser(User user){
        List<User> users = new ArrayList<>();
        users.add(user);
        return user;
    }


}
