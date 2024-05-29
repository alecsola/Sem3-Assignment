package fontys.sem3.school.controller;

import fontys.sem3.school.business.Converter.ActorNotFoundException;
import fontys.sem3.school.business.Converter.Converter;
import fontys.sem3.school.business.Request.LoginRequest;
import fontys.sem3.school.business.Request.User.CreateUserRequest;

import fontys.sem3.school.business.Response.User.GetUserResponse;
import fontys.sem3.school.business.Response.User.LoginResponse;
import fontys.sem3.school.business.interfaces.User.IUserService;
import fontys.sem3.school.business.servicesInterfaces.IAuthService;
import fontys.sem3.school.domain.User;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/user")
@AllArgsConstructor
@CrossOrigin(origins = "http://localhost:5173")
public class UserController  {
    private final Converter converter;
    private final IUserService userService;
    private final IAuthService authService;

    @PostMapping()
    public long createUser(@RequestBody @Valid CreateUserRequest userRequest){
        return userService.saveNewUser(userRequest);
    }
    @GetMapping("/get/{userId}")
    public GetUserResponse getUser(@RequestHeader("Authorization") String authorizationHeader,@PathVariable Long userId) throws ActorNotFoundException {
        String token = extractTokenFromAuthorizationHeader(authorizationHeader);
        long userToken = authService.extractUserIdFromToken(token);
        if (userToken == userId){
            return userService.getUser(userId);
        }
     else {
        throw new ActorNotFoundException();
    }
    }
    @PostMapping("admin")
    public Long saveNewAdmin(@RequestBody @Valid CreateUserRequest request){
        return userService.saveNewAdmin(request);
    }
    private String extractTokenFromAuthorizationHeader(String authorizationHeader) {
        return authorizationHeader.replaceFirst("Bearer ", "").trim();
    }





}
