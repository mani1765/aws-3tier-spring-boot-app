package com.example.controller;

import com.example.model.User;
import com.example.service.AuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
public class AuthController {

    @Autowired
    private AuthService authService;

    @GetMapping("/signup")
    public String signupPage() {
        return "signup";
    }

    @PostMapping("/signup")
    public String signUp(@ModelAttribute User user, Model model) {
        authService.signUp(user);
        model.addAttribute("message", "Congrats! Account created successfully");
        model.addAttribute("name", user.getFirstName());
        return "home";
    }

    @GetMapping("/signin")
    public String signinPage() {
        return "signin";
    }

    @PostMapping("/signin")
    public String signIn(@RequestParam String email, @RequestParam String password, Model model) {
        return authService.signIn(email, password).map(user -> {
            model.addAttribute("name", user.getFirstName());
            return "home";
        }).orElseGet(() -> {
            model.addAttribute("error", "Please enter valid email or password");
            return "signin";
        });
    }

    @GetMapping("/logout")
    public String logout() {
        return "signin";
    }
}

