package com.davidvilcabana.eva3.app_login_security.controllers;

import java.util.Optional;

import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.davidvilcabana.eva3.app_login_security.domain.user.User;
import com.davidvilcabana.eva3.app_login_security.dto.LoginRequestDTO;
import com.davidvilcabana.eva3.app_login_security.dto.ResgisterRequestDTO;
import com.davidvilcabana.eva3.app_login_security.dto.ResponseDTO;
import com.davidvilcabana.eva3.app_login_security.infra.segurity.TokenService;
import com.davidvilcabana.eva3.app_login_security.repositories.UserRepository;

import lombok.RequiredArgsConstructor;

@RestController // Marca la clase como un controlador REST
@RequestMapping("/auth") // Mapea las solicitudes que empiecen con /auth a este controlador
@RequiredArgsConstructor // Genera un constructor con los campos finales (final)

public class AuthController {
    private final UserRepository repository; // Repositorio para acceder a los datos de los usuarios
    private final PasswordEncoder passwordEncoder;// Codificador de contraseñas
    private final TokenService tokenService;// Servicio para generar tokens

    @PostMapping("/login/davidvilcabana") // Mapea las solicitudes POST a /auth/login a este método
    public ResponseEntity login(@RequestBody LoginRequestDTO body) {
        User user = this.repository.findByEmail(body.email()).orElseThrow(() -> new RuntimeException("User not found"));
        if (passwordEncoder.matches(body.password(), user.getPassword())) {
             // Genera un token si la contraseña es correcta
            String token = this.tokenService.generateToken(user);
            // Retorna una respuesta con el nombre del usuario y el token
            return ResponseEntity.ok(new ResponseDTO(user.getName(), token));
        } 
        // Retorna una respuesta de error si la contraseña es incorrecta
        return ResponseEntity.badRequest().build();
    }

    @PostMapping("/register") // Mapea las solicitudes POST a /auth/register a este método
    public ResponseEntity register(@RequestBody ResgisterRequestDTO body) {
        // Verifica si el usuario ya existe
        Optional<User> user = this.repository.findByEmail(body.email());
        // Si el usuario no existe, lo crea
        if (user.isEmpty()) {
            User newUser = new User();
            // Codifica la contraseña antes de guardarla
            newUser.setPassword(passwordEncoder.encode(body.password()));
            newUser.setEmail(body.email());
            newUser.setName(body.name());
              // Guarda el nuevo usuario en el repositorio
            this.repository.save(newUser);
 // Genera un token para el nuevo usuario
            String token = this.tokenService.generateToken(newUser);
            return ResponseEntity.ok(new ResponseDTO(newUser.getName(), token));
        }
        // Retorna una respuesta de error si el usuario ya existe
        return ResponseEntity.badRequest().build();
    }

}
