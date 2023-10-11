package fontys.sem3.school.business.impl;

import fontys.sem3.school.business.interfaces.DeleteUserUseCase;
import fontys.sem3.school.business.interfaces.IUserRepository;
import fontys.sem3.school.domain.User;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class DeleteUserUseCaseImpl implements DeleteUserUseCase {


    private final IUserRepository userRepository;
    public boolean deleteUser(User user){
        List<User> users = userRepository.GetUser();
        if(users.contains(user)){
            users.remove(user);
            return true;
        }
        return false;
    }
}
