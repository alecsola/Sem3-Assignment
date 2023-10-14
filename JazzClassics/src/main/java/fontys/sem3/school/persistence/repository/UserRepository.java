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
    private static Long NEXT_ID = 1L;

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
        List<User> testUsers = Arrays.asList(new User(1L,"Alec","sola.alec@gmail.com","solaalec","12345"), new User(2L,"Max","maxense.sola@gmail.com","max","123"));
        return Collections.unmodifiableList(testUsers);
    }


    public User saveUser(User newuser){
        newuser.setUserId(NEXT_ID);
        setNextId(NEXT_ID);
        users.add(newuser);
        return newuser;
    }
    public boolean deleteUser(User user){
        users.remove(user);
        return true;
    }
    public void setNextId(Long id) {
        NEXT_ID++;
    }

}
