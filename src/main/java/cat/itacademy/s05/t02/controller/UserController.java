package cat.itacademy.s05.t02.controller;

import cat.itacademy.s05.t02.model.User;
import cat.itacademy.s05.t02.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/user")
@CrossOrigin
public class UserController {

    @Autowired
    private UserService service;

    @PutMapping("/update")
    public ResponseEntity<User> update(@RequestBody User partialUser) {
        String email = SecurityContextHolder.getContext().getAuthentication().getName();
        User existingUser = service.findByEmail(email);
        if (partialUser.getId() != 0 && partialUser.getId()!=(existingUser.getId()) ) {
            return ResponseEntity.status(HttpStatus.FORBIDDEN).body(null); // o .build() si no quieres body
        }
        existingUser.setPhoto_url(partialUser.getPhoto_url());
        return ResponseEntity.ok(service.update(existingUser));
    }
}
