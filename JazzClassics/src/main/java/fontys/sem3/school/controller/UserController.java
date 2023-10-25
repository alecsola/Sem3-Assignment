package fontys.sem3.school.controller;

import fontys.sem3.school.business.Converter.Converter;
import fontys.sem3.school.business.Request.CreateUserRequest;
import fontys.sem3.school.business.Request.LoginRequest;
import fontys.sem3.school.business.Request.UpdateUserRequest;
import fontys.sem3.school.business.Response.CreateUserResponse;
import fontys.sem3.school.business.Response.GetUserResponse;
import fontys.sem3.school.business.impl.AuthenticationService;
import fontys.sem3.school.business.interfaces.UserUseCase;
import fontys.sem3.school.domain.User;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Collections;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/user")
@AllArgsConstructor
@CrossOrigin(origins = "http://localhost:5173")
public class UserController  {
    private final Converter converter;
    private final UserUseCase userUseCase;
    private final AuthenticationService authenticationService;

    @GetMapping
    public ResponseEntity<List<User>> getUser(){

        List<User> users = userUseCase.GetUser();
        System.out.println("Users fetched: " + users); // Add appropriate logging
        return ResponseEntity.ok(users);
    }
    @GetMapping("getUserbyId")
    public ResponseEntity<User> getUserbyId(@RequestParam Long id){

        try {
            User user = userUseCase.getUserbyId(id);
            if (user != null) {
                System.out.println("User retrieved: " + user);
                return ResponseEntity.ok(user);
            } else {
                System.out.println("User not found for id: " + id);
                return ResponseEntity.notFound().build();
            }
        } catch (Exception e) {
            System.err.println("Error retrieving user for id " + id + ": " + e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }




    @PostMapping
    public ResponseEntity<CreateUserResponse> createUser(
                 @Valid @RequestBody CreateUserRequest createUserRequest) {
        User user = converter.userRequestConverter(createUserRequest);

        // Create the user using the createUserUseCase
        User createdUser = userUseCase.createUser(user);

        // Convert the created User into CreateUserResponse
        CreateUserResponse response = converter.responseConverter(createdUser);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }
    @PostMapping("/login")
    public ResponseEntity<User> validateUserCredentials(@RequestBody LoginRequest loginRequest) {
        try {
            User user = authenticationService.validateUserCredentials(loginRequest.getUsername(), loginRequest.getPassword());
            return ResponseEntity.ok().contentType(MediaType.APPLICATION_JSON).body(user);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).contentType(MediaType.APPLICATION_JSON)
                    .body(null); // Or handle the error response accordingly
        }
    }
    @PostMapping("/updateUser")
    public ResponseEntity<CreateUserResponse> updateUser(@RequestBody UpdateUserRequest updateUserRequest) {
        User updatedUser = userUseCase.updateUser(updateUserRequest.getId(), updateUserRequest.getName(), updateUserRequest.getUsername(), updateUserRequest.getEmail(), updateUserRequest.getPassword());
        CreateUserResponse response = converter.responseConverter(updatedUser);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

}
