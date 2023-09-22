package fontys.sem3.school.controller;

import fontys.sem3.school.business.interfaces.GetUserUseCase;
import fontys.sem3.school.domain.GetUserResponse;
import fontys.sem3.school.domain.User;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/user")
@AllArgsConstructor
public class UserController  {
    private final GetUserUseCase getUserUseCase;

    @GetMapping
    public ResponseEntity<GetUserResponse> getUser(){
        return ResponseEntity.ok(getUserUseCase.GetUser());
    }
}
