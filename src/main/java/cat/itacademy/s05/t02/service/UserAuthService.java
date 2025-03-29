package cat.itacademy.s05.t02.service;

import cat.itacademy.s05.t02.dtos.AuthRequest;
import cat.itacademy.s05.t02.model.User;
import cat.itacademy.s05.t02.repository.UserRepository;
import cat.itacademy.s05.t02.security.JwtUtil;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserAuthService {

    private final UserRepository userRepository;
    private final JwtUtil jwtUtil;
    private final PasswordEncoder passwordEncoder;

    public UserAuthService(UserRepository userRepository, JwtUtil jwtUtil) {
        this.userRepository = userRepository;
        this.jwtUtil = jwtUtil;
        this.passwordEncoder = new BCryptPasswordEncoder();
    }

    public void registerUser(User user) {
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        userRepository.save(user);
    }

    public String authenticate(AuthRequest authRequest) {
        Optional<User> userOpt = userRepository.findByEmail(authRequest.getEmail());

        if (userOpt.isPresent()) {
            User user = userOpt.get();
            if (passwordEncoder.matches(authRequest.getPassword(), user.getPassword())) {
                return jwtUtil.generateToken(user.getEmail());
            }
        }
        throw new RuntimeException("Credenciales incorrectas");
    }
}
