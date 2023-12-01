package fontys.sem3.school.business.impl;

import fontys.sem3.school.business.Converter.Converter;
import fontys.sem3.school.business.Request.User.CreateUserRequest;
import fontys.sem3.school.business.Response.User.CreateUserResponse;
import fontys.sem3.school.business.Response.User.GetUserResponse;
import fontys.sem3.school.business.Response.User.GetUsersResponse;
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
    private final PasswordEncoder passwordEncoder;
    /**
     * @should save a user and return id
     * @should return an error when it cant be created
     */
    public Long saveNewUser(CreateUserRequest request) {
        User user = Converter.userRequestConverter(request);
        long result = userRepository.saveNewUser(user);

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




}
