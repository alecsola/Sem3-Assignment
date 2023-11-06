package fontys.sem3.school.persistence.repository;

import fontys.sem3.school.business.interfaces.User.IUserRepository;
import fontys.sem3.school.domain.User;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.Collections;
import java.util.List;

@Repository
@RequiredArgsConstructor
public class UserRepository implements IUserRepository {

private final List<User> users;
    private static Long NEXT_ID = 1L;



    @Override
    public User getUserbyId(Long id){

        for (User user: users){
            if(user.getId().equals(id)){
                return user;
            }
        }
        return null;
    }


    @Override
    public List<User> GetUser() {

        return Collections.unmodifiableList(users);
    }


    public User saveUser(User newuser){
        newuser.setId(NEXT_ID);
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
