package cat.itacademy.s05.t02.controller;

import cat.itacademy.s05.t02.dtos.AuthResponse;
import cat.itacademy.s05.t02.exception.UserNotFoundException;
import cat.itacademy.s05.t02.model.User;
import cat.itacademy.s05.t02.service.UserAuthService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/auth")
@CrossOrigin
public class UserAuthController {

    private final UserAuthService authService;

    public UserAuthController(@RequestBody UserAuthService authService) {
        this.authService = authService;
    }

    @PostMapping("/register")
    public ResponseEntity<Map<String, String>> register(@RequestBody User user) {
        if (authService.existsByEmail(user.getEmail())) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body(Map.of("message", "Ya existe una cuenta asociada a este correo electrónico. Por favor, inicia sesión o usa otro correo para registrarte."));
        }
        authService.registerUser(user);
        return ResponseEntity.ok(Map.of("message", "Usuario registrado exitosamente"));
    }

    @PostMapping("/login")
    public ResponseEntity<AuthResponse> login(@RequestBody User user) {
        return ResponseEntity.ok(new AuthResponse(authService.authenticate(user)));
    }

    @GetMapping("/{idusers}")
    public ResponseEntity<User> getUserById(@PathVariable int id) {
        User user = authService.findById(id);
        if (user == null) {
            throw new UserNotFoundException("User not found with id: " + id);
        }
        return ResponseEntity.ok(user);
    }
}
