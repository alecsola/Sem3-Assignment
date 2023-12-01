package fontys.sem3.school.controller;

import fontys.sem3.school.business.Converter.Converter;
import fontys.sem3.school.business.Request.LoginRequest;
import fontys.sem3.school.business.Request.User.CreateUserRequest;

import fontys.sem3.school.business.Response.User.GetUserResponse;
import fontys.sem3.school.business.Response.User.LoginResponse;
import fontys.sem3.school.business.interfaces.User.IUserService;
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


    @PostMapping()
    public long createUser(@RequestBody @Valid CreateUserRequest userRequest){
        return userService.saveNewUser(userRequest);
    }
    @GetMapping("/get")
    public GetUserResponse getUser(@RequestParam Long id){
        return userService.getUser(id);
    }




}
