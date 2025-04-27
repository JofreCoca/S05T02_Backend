package cat.itacademy.s05.t02.service;

import cat.itacademy.s05.t02.exception.DogNotFoundException;
import cat.itacademy.s05.t02.exception.UserNotFoundException;
import cat.itacademy.s05.t02.model.Dog;
import cat.itacademy.s05.t02.model.User;
import cat.itacademy.s05.t02.repository.DogRepository;
import cat.itacademy.s05.t02.repository.UserRepository;
import cat.itacademy.s05.t02.security.JwtUtil;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class DogService {
    @Autowired
    private DogRepository dogRepository;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private JwtUtil jwtUtil;

    public void create(Dog dog) {
        User user = userRepository.findById(dog.getUsers_idusers())
                .orElseThrow(() -> new RuntimeException("Usuario no encontrado"));
        dogRepository.save(dog);
    }

    public void delete(Dog dog) {
        User user = userRepository.findById(dog.getUsers_idusers())
                .orElseThrow(() -> new RuntimeException("Usuario no encontrado"));
        if (dog.getIddogs() == 0 || !dogRepository.existsById(dog.getIddogs())) {
            throw new DogNotFoundException("The id is null or the dog no exist");
        }
        dogRepository.delete(dog);
    }

    public void update(Dog dog) {
        User user = userRepository.findById(dog.getUsers_idusers())
                .orElseThrow(() -> new RuntimeException("Usuario no encontrado"));
        System.out.println(dog.getIddogs());
        if (dog.getIddogs() == 0 || !dogRepository.existsById(dog.getIddogs())) {
            throw new DogNotFoundException("The id is null or the dog no exist");
        }
        dogRepository.save(dog);
    }

    public List<Dog> getAll(HttpServletRequest request) {
        String token = request.getHeader("Authorization").replace("Bearer ", "");
        String username = jwtUtil.extractEmail(token);
        User user = userRepository.findByEmail(username)
                .orElseThrow(() -> new UserNotFoundException("player not found."));
        return dogRepository.findAll()
                .stream()
                .filter(dog -> dog.getUsers_idusers() == user.getId())
                .collect(Collectors.toList());
    }

}
