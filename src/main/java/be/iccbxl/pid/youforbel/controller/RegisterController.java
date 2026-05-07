package be.iccbxl.pid.youforbel.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import be.iccbxl.pid.youforbel.model.User;
import be.iccbxl.pid.youforbel.model.UserRole;
import be.iccbxl.pid.youforbel.repository.UserRepository;

import jakarta.validation.Valid;

@Controller
public class RegisterController {

    @Autowired
    private UserRepository repository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    // ===============================
    // FORMULAIRE INSCRIPTION
    // ===============================
    @GetMapping("/register")
    public String registerForm(Model model) {

        model.addAttribute("user", new User());
        model.addAttribute("title", "Inscription");

        return "register";
    }

    // ===============================
    // ENREGISTREMENT UTILISATEUR
    // ===============================
    @PostMapping("/register")
    public String registerSubmit(@Valid @ModelAttribute User user,
                                 BindingResult bindingResult,
                                 Model model) {

        // Vérification erreurs formulaire
        if (bindingResult.hasErrors()) {

            model.addAttribute("title", "Inscription");

            return "register";
        }

        // Vérification login déjà utilisé
        if (repository.findByLogin(user.getLogin()) != null) {

            model.addAttribute("errorMessage",
                    "Ce login existe déjà.");

            model.addAttribute("title", "Inscription");

            return "register";
        }

        // Hash mot de passe
        user.setPassword(
                passwordEncoder.encode(user.getPassword())
        );

        // Rôle par défaut
        user.setRole(UserRole.MEMBER);

        // Sauvegarde
        repository.save(user);

        return "redirect:/login";
    }
}
