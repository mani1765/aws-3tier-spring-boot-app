package com.example.controller;

import com.example.model.User;
import com.example.service.AuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/api")
public class AuthController {

    @Autowired
    private AuthService authService;

    // Sign-Up Endpoint
    @PostMapping("/signup")
    public ResponseEntity<?> signUp(@RequestBody User user) {
        try {
            authService.signUp(user);
            return ResponseEntity.ok("Congrats! Account created successfully.");
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("Error: " + e.getMessage());
        }
    }

    // Sign-In Endpoint
    @PostMapping("/signin")
    public ResponseEntity<?> signIn(@RequestBody User loginRequest) {
        Optional<User> userOptional = authService.signIn(loginRequest.getEmail(), loginRequest.getPassword());

        if (userOptional.isPresent()) {
            User user = userOptional.get();
            return ResponseEntity.ok(user); // Return the authenticated user object as a JSON response
        } else {
            return ResponseEntity.status(401).body("Invalid email or password."); // Authentication failed
        }
    }
}

