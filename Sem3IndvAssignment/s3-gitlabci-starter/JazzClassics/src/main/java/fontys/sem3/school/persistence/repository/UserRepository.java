package fontys.sem3.school.persistence.repository;

import fontys.sem3.school.business.IUserRepository;
import fontys.sem3.school.domain.User;

import java.util.ArrayList;
import java.util.List;

public class UserRepository implements IUserRepository {

    @Override
    public User findbyId(Long id){
        User user = new User();
        if(user.getUserId() == id){
            return user;
        }
        return null;
    }

    @Override
    public List<User> GetAllUsers(){

        List<User> users = new ArrayList<>(); // Initialize an empty list

        // Add user objects to the list (you can replace this with your actual logic)
        users.add(new User(1L,"Alec","sola.alec@gmail.com", "123"));
        users.add(new User(2L, "Juan", "juan@gmail.com", "123"));


        return users; // Return the list of users


    }

    @Override
    public void AddUser(){


    }
}
