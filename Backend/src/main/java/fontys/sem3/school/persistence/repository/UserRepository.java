package fontys.sem3.school.persistence.repository;

import fontys.sem3.school.business.interfaces.User.IUserRepositoryBusiness;
import fontys.sem3.school.domain.User;
import fontys.sem3.school.persistence.IUserRepository;
import fontys.sem3.school.persistence.JPAmappers.UserJPAmapper;
import fontys.sem3.school.persistence.converters.UserConverter;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
@RequiredArgsConstructor
public class UserRepository implements IUserRepositoryBusiness {

    private final List<User> users;
    private final PasswordEncoder passwordEncoder;
    private final IUserRepository userRepository;
    private static Long NEXT_ID = 1L;

    @Transactional
    public Long saveNewUser(User user) {
        if (user.getPassword().length() < 4) {
            throw new IllegalArgumentException("Password is too short");
        }
        UserJPAmapper userJPAmapper = UserConverter.userConverter(user);
        String encodedPassword = passwordEncoder.encode(user.getPassword());
        userJPAmapper.setPassword(encodedPassword);
        return userRepository.save(userJPAmapper).getId();

    }
    @Transactional
    public Long saveNewAdmin(User user) {
        if (user.getPassword().length() < 4) {
            throw new IllegalArgumentException("Password is too short");
        }
        UserJPAmapper userJPAmapper = UserConverter.userConverterAdmin(user);
        String encodedPassword = passwordEncoder.encode(user.getPassword());
        userJPAmapper.setPassword(encodedPassword);
        return userRepository.save(userJPAmapper).getId();

    }
    @Transactional
    public User findUserbyUsername(String username){
        UserJPAmapper userJPA = userRepository.findByUsername(username);
        User user = UserConverter.userJPAmapperConverter(userJPA);
        return user;
    }
    public User getUser(Long id){
            List<UserJPAmapper> userJPAs = userRepository.findAll();
        for(UserJPAmapper userJPA:userJPAs){
            if(userJPA.getId().equals(id)){
                User user = UserConverter.userJPAmapperConverter(userJPA);
                return user;
            }
        }

        return null;
    }

    public User updateUser(User user){
        UserJPAmapper userJPAmapper = userRepository.findById(user.getId());
        if (userJPAmapper !=null){
            userJPAmapper.setName(user.getName());
            userJPAmapper.setUsername(user.getUsername());
            userJPAmapper.setPassword(user.getPassword());
            userJPAmapper.setEmail(user.getEmail());
            userRepository.save(userJPAmapper);
            return UserConverter.userJPAmapperConverter(userJPAmapper);

        }
        return null;
    }


    public User findUserbyUsernameWithRoles(String username){
        UserJPAmapper userJPA = userRepository.findUserbyUsernameWithRoles(username);
        User user = UserConverter.userJPAmapperConverter(userJPA);
        return user;
    }


    public void setNextId(Long id) {
        NEXT_ID++;
    }

}
