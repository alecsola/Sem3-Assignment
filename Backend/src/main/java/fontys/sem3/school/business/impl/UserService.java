package fontys.sem3.school.business.impl;

import fontys.sem3.school.business.Converter.Converter;
import fontys.sem3.school.business.Request.User.CreateUserRequest;
import fontys.sem3.school.business.Request.User.UpdateUserRequest;
import fontys.sem3.school.business.Response.User.CreateUserResponse;
import fontys.sem3.school.business.Response.User.GetUserResponse;
import fontys.sem3.school.business.Response.User.GetUsersResponse;
import fontys.sem3.school.business.Response.User.UpdateUserResponse;
import fontys.sem3.school.business.interfaces.User.IUserRepositoryBusiness;
import fontys.sem3.school.business.interfaces.User.IUserService;
import fontys.sem3.school.domain.User;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserService implements IUserService {


    private final IUserRepositoryBusiness userRepository;

    /**
     * @should save a user and return id
     * @should return an error when it cant be created
     */
    public Long saveNewUser(CreateUserRequest request) {
        if (request.getPassword().length() < 4) {
            throw new IllegalArgumentException("Password is too short");
        }
        User user = Converter.userRequestConverter(request);
        long result = userRepository.saveNewUser(user);
        if(result==0){
            return -1L;
        }
        return new CreateUserResponse(result).getId();
    }
    /**
     * @should get a user by id
     * @should return an error user is not found
     */
    public GetUserResponse getUser (long id){
        User user = userRepository.getUser(id);
        return Converter.userRequestConverter(user);
    }
    public Long saveNewAdmin(CreateUserRequest request){
        if (request.getPassword().length() < 4) {
            throw new IllegalArgumentException("Password is too short");
        }
        User user = Converter.userRequestConverter(request);
        long result = userRepository.saveNewAdmin(user);
        if(result==0){
            return -1L;
        }
        return new CreateUserResponse(result).getId();
    }

//    public UpdateUserResponse updateUser(UpdateUserRequest request){
//        userRepository.updateUser()
//        User user = Converter.updateUserRequestConv(request);
//    }




}
