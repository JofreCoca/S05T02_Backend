package cat.itacademy.s05.t02.service;

import cat.itacademy.s05.t02.exception.UserNotFoundException;
import cat.itacademy.s05.t02.model.User;
import cat.itacademy.s05.t02.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {
    @Autowired
    private UserRepository userRepository;

    public User update(User user) {
        User existingUser = userRepository.findById(user.getId())
                .orElseThrow(() -> new RuntimeException("Usuario no encontrado"));
        existingUser.setPhoto_url(user.getPhoto_url());
        return userRepository.save(existingUser);
    }

    public User findByEmail(String email) {
        return userRepository.findByEmail(email)
                .orElseThrow(() -> new RuntimeException("Usuario no encontrado con email: " + email));
    }

}
