package com.prueba.PruebaSpring.controller;

import com.prueba.PruebaSpring.models.Role;
import com.prueba.PruebaSpring.models.User;
import com.prueba.PruebaSpring.repository.UserRepository;
import com.prueba.PruebaSpring.security.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/auth")
@CrossOrigin(origins = "http://localhost:4200") // importante para Angular
public class AuthController {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private JwtUtil jwtUtil;

    @Autowired
    private PasswordEncoder passwordEncoder;

    // =========================
    // REGISTER
    // =========================
    @PostMapping("/register")
    public ResponseEntity<?> register(@RequestBody User user) {

        // Encriptar contraseña
        user.setPassword(passwordEncoder.encode(user.getPassword()));

        // Asignar rol USER por defecto
        user.setRole(Role.USER);

        userRepository.save(user);

        // Generar token
        String token = jwtUtil.generateToken(user.getEmail(), user.getRole().name());

        // Devolver JSON
        return ResponseEntity.ok(Map.of("token", token));
    }

    // =========================
    // LOGIN
    // =========================
    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody User loginRequest) {

        User user = userRepository.findByEmail(loginRequest.getEmail())
                .orElseThrow(() -> new RuntimeException("Usuario no encontrado"));

        if (!passwordEncoder.matches(loginRequest.getPassword(), user.getPassword())) {
            return ResponseEntity.badRequest()
                    .body(Map.of("error", "Credenciales incorrectas"));
        }

        String token = jwtUtil.generateToken(user.getEmail(), user.getRole().name());

        return ResponseEntity.ok(Map.of("token", token));
    }
}