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
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

@RestController
@RequestMapping("/user")
@CrossOrigin
public class UserController {

    @Autowired
    private UserService service;

    private static final String UPLOAD_DIR = "uploads/";

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

    @PostMapping("/upload")
    public ResponseEntity<String> uploadImage(@RequestParam("file") MultipartFile file) {
        System.out.println("enter upload");
        // Verificar que el archivo no está vacío
        if (file.isEmpty()) {
            return ResponseEntity.badRequest().body("No se seleccionó ningún archivo");
        }

        try {
            // Crear un directorio para almacenar la imagen si no existe
            Path path = Paths.get(UPLOAD_DIR);
            if (!Files.exists(path)) {
                Files.createDirectories(path);
            }

            // Guardar el archivo en el servidor
            Path filePath = path.resolve(file.getOriginalFilename());
            file.transferTo(filePath.toFile());

            // Devolver la URL de la imagen guardada
            String fileUrl = "http://localhost:8080/" + UPLOAD_DIR + file.getOriginalFilename();
            return ResponseEntity.ok(fileUrl);

        } catch (IOException e) {
            e.printStackTrace();
            return ResponseEntity.status(500).body("Error al guardar la imagen");
        }
    }


}
