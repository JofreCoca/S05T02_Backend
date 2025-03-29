package cat.itacademy.s05.t02.service;

import cat.itacademy.s05.t02.model.User;
import cat.itacademy.s05.t02.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {
    @Autowired
    private UserRepository userRepository;


    public User add(User user){
        return userRepository.save(user);
    }
}
