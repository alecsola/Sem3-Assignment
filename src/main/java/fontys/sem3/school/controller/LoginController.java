package fontys.sem3.school.controller;

import fontys.sem3.school.business.Request.LoginRequest;
import fontys.sem3.school.business.Response.User.LoginResponse;
import fontys.sem3.school.business.interfaces.User.ILoginService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/tokens")
@AllArgsConstructor
@CrossOrigin(origins = "http://localhost:5173")
public class LoginController {

private final ILoginService loginService;
    @PostMapping("login")
    public ResponseEntity<LoginResponse> login(@RequestBody LoginRequest loginRequest) {
        LoginResponse loginResponse = loginService.login(loginRequest);
        return ResponseEntity.status(HttpStatus.CREATED).body(loginResponse);
    }
}
