package be.iccbxl.pid.youforbel.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import be.iccbxl.pid.youforbel.model.User;
import be.iccbxl.pid.youforbel.repository.UserRepository;

@Controller
public class ProfileController {

    @Autowired
    private UserRepository userRepository;

    @GetMapping("/profile")
    public String profile(Authentication authentication, Model model) {

        if (authentication == null || !authentication.isAuthenticated()) {
            return "redirect:/login";
        }

        String login = authentication.getName();

        User user = userRepository.findByLogin(login);

        if (user == null) {
            model.addAttribute("errorMessage", "Utilisateur introuvable.");
            return "redirect:/login";
        }

        model.addAttribute("user", user);
        model.addAttribute("title", "Mon profil");

        return "profile";
    }
}