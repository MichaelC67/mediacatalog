package com.example.mediacatalog.controller;

import com.example.mediacatalog.model.User;
import com.example.mediacatalog.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/users")
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping("/register")
    public String showRegistrationForm(Model model) {
        model.addAttribute("user", new User());
        return "register";
    }

    @PostMapping("/register")
    public String registerUser(@ModelAttribute User user, Model model) {
        if (userService.existsByUsername(user.getUsername())) {
            model.addAttribute("error", "Ce nom d'utilisateur existe déjà.");
            model.addAttribute("user", user);
            return "register";
        }
        
        if (userService.existsByEmail(user.getEmail())) {
            model.addAttribute("error", "Cet email existe déjà.");
            model.addAttribute("user", user);
            return "register";
        }
        
        userService.registerUser(user, "user");
        return "redirect:/users/login";
    }

    @GetMapping("/login")
    public String showLoginForm() {
        return "login";
    }

    @GetMapping("/list")
    public String getAllUsers(Model model) {
        model.addAttribute("users", userService.getAllUsers());
        return "users";
    }

    @GetMapping("/add")
    public String showAddUserForm(Model model) {
        model.addAttribute("user", new User());
        return "add-user";
    }

    @PostMapping("/save")
    public String saveUser(@ModelAttribute User user, @RequestParam(defaultValue = "user") String role) {
        userService.registerUser(user, role);
        return "redirect:/users/list";
    }

    @GetMapping("/delete/{id}")
    public String deleteUser(@PathVariable Long id) {
        userService.deleteUser(id);
        return "redirect:/users/list";
    }

    @GetMapping("/profile")
    public String showProfile(Model model) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String username = authentication.getName();
        User user = userService.findByUsername(username);
        model.addAttribute("user", user);
        return "profile";
    }

    @PostMapping("/change-password")
    public String changePassword(@RequestParam String currentPassword, @RequestParam String newPassword, @RequestParam String confirmPassword, Model model) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String username = authentication.getName();
        User user = userService.findByUsername(username);

        if (!newPassword.equals(confirmPassword)) {
            model.addAttribute("error", "Les nouveaux mots de passe ne correspondent pas.");
            model.addAttribute("user", user);
            return "profile";
        }

        userService.changePassword(user, currentPassword, newPassword);
        model.addAttribute("success", "Mot de passe changé avec succès.");
        model.addAttribute("user", user);
        return "profile";
    }
}
