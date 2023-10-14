package fontys.sem3.school.controller;

import fontys.sem3.school.business.Converter.Converter;
import fontys.sem3.school.business.Request.CreateUserRequest;
import fontys.sem3.school.business.Response.CreateUserResponse;
import fontys.sem3.school.business.Response.GetUserResponse;
import fontys.sem3.school.business.interfaces.UserUseCase;
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
    private final UserUseCase userUseCase;

    @GetMapping
    public ResponseEntity<GetUserResponse> getUser(){

        return ResponseEntity.ok(userUseCase.GetUser());
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

}
