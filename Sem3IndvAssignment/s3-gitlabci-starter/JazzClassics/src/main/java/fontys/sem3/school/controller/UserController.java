package fontys.sem3.school.controller;

import fontys.sem3.school.business.interfaces.CreateUserUseCase;
import fontys.sem3.school.business.interfaces.GetUserUseCase;
import fontys.sem3.school.controller.Converter.Converter;
import fontys.sem3.school.controller.Request.CreateUserRequest;
import fontys.sem3.school.controller.Response.CreateUserResponse;
import fontys.sem3.school.controller.Response.GetUserResponse;
import fontys.sem3.school.domain.User;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/user")
@AllArgsConstructor

public class UserController  {
    private final GetUserUseCase getUserUseCase;
    private final CreateUserUseCase createUserUseCase;
    private final Converter converter;

    @GetMapping
    public ResponseEntity<GetUserResponse> getUser(){

        return ResponseEntity.ok(getUserUseCase.GetUser());
    }


    @PostMapping

    public ResponseEntity<CreateUserResponse> createUser(
            @RequestBody @Valid CreateUserRequest createUserRequest) {
        User user = converter.userRequestConverter(createUserRequest);

        // Create the user using the createUserUseCase
        User createdUser = createUserUseCase.createUser(user);

        // Convert the created User into CreateUserResponse
        CreateUserResponse response = converter.responseConverter(createdUser);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

}
