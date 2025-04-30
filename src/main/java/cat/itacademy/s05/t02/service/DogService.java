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

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
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


    private final Path dogPhotosLocation = Paths.get("/Users/jofrecocaavila/Downloads/S05T02_Backend/src/main/java/cat/itacademy/s05/t02/uploads/dogs");


    public void create(Dog dog) {
        User user = userRepository.findById(dog.getUsers_idusers())
                .orElseThrow(() -> new RuntimeException("Usuario no encontrado"));
        dogRepository.save(dog);
    }

    public void delete(int id, HttpServletRequest request) {
        String token = request.getHeader("Authorization").replace("Bearer " , "");
        String username = JwtUtil.extractEmail(token);
        String role = JwtUtil.extractRole(token);
        User user = userRepository.findByEmail(username)
                .orElseThrow(() -> new UserNotFoundException("User not found"));
        Dog dog = dogRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Dog not found"));
        if(role.equals("USER") && dog.getUsers_idusers()!=(user.getId())) {
            throw new RuntimeException("This dog doesn't belong to you");
        }
        dogRepository.delete(dog);
    }

    public void update(Dog dog) {
        User user = userRepository.findById(dog.getUsers_idusers())
                .orElseThrow(() -> new RuntimeException("Usuario no encontrado"));
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
        if(user.getRole().name().equals("ADMIN")){
            return dogRepository.findAll();
        }else{
            return dogRepository.findAll()
                    .stream()
                    .filter(dog -> dog.getUsers_idusers() == user.getId())
                    .collect(Collectors.toList());
        }
    }
}
