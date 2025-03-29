package cat.itacademy.s05.t02.controller;

import cat.itacademy.s05.t02.dtos.AuthRequest;
import cat.itacademy.s05.t02.dtos.AuthResponse;
import cat.itacademy.s05.t02.model.User;
import cat.itacademy.s05.t02.service.UserAuthService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
@CrossOrigin
public class UserAuthController {

    private final UserAuthService authService;

    public UserAuthController(@RequestBody UserAuthService authService) {
        this.authService = authService;
    }

    @PostMapping("/register")
    public ResponseEntity<String> register(@RequestBody User user) {
        authService.registerUser(user);
        return ResponseEntity.ok("Usuario registrado exitosamente");
    }

    @PostMapping("/login")
    public ResponseEntity<AuthResponse> login(@RequestBody AuthRequest authRequest) {
        String token = authService.authenticate(authRequest);
        return ResponseEntity.ok(new AuthResponse(token));
    }
}
