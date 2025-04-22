package cat.itacademy.s05.t02.controller;

import cat.itacademy.s05.t02.dtos.AuthResponse;
import cat.itacademy.s05.t02.dtos.PhotoUser;
import cat.itacademy.s05.t02.model.User;
import cat.itacademy.s05.t02.service.UserAuthService;
import cat.itacademy.s05.t02.service.UserService;
import org.apache.tomcat.util.net.openssl.ciphers.Authentication;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;

@RestController
@RequestMapping("/user")
@CrossOrigin
public class UserController {

    @Autowired
    private UserService service;

    @PutMapping("/update")
    public ResponseEntity<User> update(@RequestBody User user) {
        System.out.println("Entrando a /user/update");
        return ResponseEntity.ok(service.update(user));
    }

}
