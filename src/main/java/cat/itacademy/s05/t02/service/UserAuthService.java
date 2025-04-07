package cat.itacademy.s05.t02.service;

import cat.itacademy.s05.t02.dtos.AuthRequest;
import cat.itacademy.s05.t02.model.User;
import cat.itacademy.s05.t02.repository.UserRepository;
import cat.itacademy.s05.t02.security.JwtUtil;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Optional;

@Service
public class UserAuthService {

    private final UserRepository userRepository;
    private final JwtUtil jwtUtil;
    private final PasswordEncoder passwordEncoder;

    public User findByEmail(String email) {
        return userRepository.findByEmail(email).orElse(null);
    }

    public User findById(int idusers) {
        return userRepository.findById(idusers).orElseThrow(() -> new RuntimeException("User not found"));
    }

    public UserAuthService(UserRepository userRepository, JwtUtil jwtUtil) {
        this.userRepository = userRepository;
        this.jwtUtil = jwtUtil;
        this.passwordEncoder = new BCryptPasswordEncoder();
    }

    public void registerUser(User user) {
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        user.setRegistration_date(LocalDateTime.now());
        userRepository.save(user);
    }

    public boolean existsByEmail(String email) {
        return userRepository.findByEmail(email).isPresent();
    }

    public String authenticate(User user) {
        Optional<User> user2 = userRepository.findByEmail(user.getEmail());
        if(user2.isPresent()){
            User userOptional = user2.get();
            if (passwordEncoder.matches((user.getPassword()), userOptional.getPassword())) {
                return jwtUtil.generateToken(user.getEmail());
            }
        }
        throw new RuntimeException("Credenciales incorrectas");
    }
}
