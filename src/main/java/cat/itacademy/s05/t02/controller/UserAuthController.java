package cat.itacademy.s05.t02.controller;

import cat.itacademy.s05.t02.dtos.AuthRequest;
import cat.itacademy.s05.t02.dtos.AuthResponse;
import cat.itacademy.s05.t02.model.User;
import cat.itacademy.s05.t02.service.UserAuthService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
public class UserAuthController {

    private final UserAuthService authService;

    public UserAuthController(UserAuthService authService) {
        this.authService = authService;
    }

    // Endpoint para registrar un usuario
    @PostMapping("/register")
    public ResponseEntity<String> register(@RequestBody User user) {
        authService.registerUser(user);
        return ResponseEntity.ok("Usuario registrado exitosamente");
    }

    // Endpoint para autenticar un usuario y obtener un token
    @PostMapping("/login")
    public ResponseEntity<AuthResponse> login(@RequestBody AuthRequest authRequest) {
        String token = authService.authenticate(authRequest);
        return ResponseEntity.ok(new AuthResponse(token));
    }
}
