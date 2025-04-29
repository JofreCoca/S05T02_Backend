package cat.itacademy.s05.t02.controller;

import cat.itacademy.s05.t02.model.Dog;
import cat.itacademy.s05.t02.model.User;
import cat.itacademy.s05.t02.service.DogService;
import jakarta.servlet.http.HttpServletRequest;
import org.apache.tomcat.util.net.openssl.ciphers.Authentication;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.net.MalformedURLException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

@RestController
@RequestMapping("/dog")
public class DogController {
    @Autowired
    private DogService dogService;
    private final Path rootLocation = Paths.get("uploads/dogs"); // Carpeta donde están las fotos


    @PostMapping("/create")
    ResponseEntity<String> create(@RequestBody Dog dog){
        dogService.create(dog);
        return ResponseEntity.ok("Dog created successfully.");
    }

    @DeleteMapping("/delete/{id}")
    ResponseEntity<String> delete(@PathVariable int id, HttpServletRequest request){
        dogService.delete(id, request);
        return ResponseEntity.ok("Dog delete successfully.");
    }

    @PutMapping ("/update")
    ResponseEntity<String> update(@RequestBody Dog dog){
        dogService.update(dog);
        return ResponseEntity.ok("Dog update successfully.");
    }

    @GetMapping("/getAll")
    public ResponseEntity<List<Dog>> getAll(HttpServletRequest request) {
        String token = request.getHeader("Authorization");
        return ResponseEntity.ok(dogService.getAll(request));
    }
}
