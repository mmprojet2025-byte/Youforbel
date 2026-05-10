package be.iccbxl.pid.youforbel.controller;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import be.iccbxl.pid.youforbel.dto.LoginRequestDTO;
import be.iccbxl.pid.youforbel.dto.LoginResponseDTO;
import be.iccbxl.pid.youforbel.model.User;
import be.iccbxl.pid.youforbel.repository.UserRepository;
import be.iccbxl.pid.youforbel.security.JwtService;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;

    public AuthController(UserRepository userRepository,
                          PasswordEncoder passwordEncoder,
                          JwtService jwtService) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.jwtService = jwtService;
    }

    @PostMapping("/login")
    public LoginResponseDTO login(@RequestBody LoginRequestDTO request) {

        User user = userRepository.findByLogin(request.getLogin());

        if (user == null) {
            throw new IllegalArgumentException("Login ou mot de passe incorrect");
        }

        boolean passwordValid =
                passwordEncoder.matches(request.getPassword(), user.getPassword());

        if (!passwordValid) {
            throw new IllegalArgumentException("Login ou mot de passe incorrect");
        }

        String token = jwtService.generateToken(user.getLogin());

        return new LoginResponseDTO(token);
    }
}
