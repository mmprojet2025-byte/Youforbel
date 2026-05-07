package be.iccbxl.pid.youforbel.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

import be.iccbxl.pid.youforbel.model.User;
import be.iccbxl.pid.youforbel.repository.UserRepository;

@Configuration
public class SecurityConfig {

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public UserDetailsService userDetailsService(UserRepository repository) {
        return username -> {
            User user = repository.findByLogin(username);

            if (user == null) {
                throw new UsernameNotFoundException("Utilisateur introuvable");
            }

            return org.springframework.security.core.userdetails.User
                    .withUsername(user.getLogin())
                    .password(user.getPassword())
                    .roles(user.getRole().name())
                    .build();
        };
    }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http
            .authorizeHttpRequests(auth -> auth

        // pages accessibles à tous les utilisateurs connectés
        .requestMatchers("/artists", "/artists/*").authenticated()

        // pages ADMIN seulement
        .requestMatchers("/artists/new").hasRole("ADMIN")
        .requestMatchers("/artists/*/edit").hasRole("ADMIN")
        .requestMatchers("/artists/*/delete").hasRole("ADMIN")

        // tout le reste
        .anyRequest().permitAll()
)
            
            .formLogin(form -> form
                .loginPage("/login")
                .defaultSuccessUrl("/artists", true)
                .permitAll()
            )
            .logout(logout -> logout
                .logoutSuccessUrl("/login")
                .permitAll()
            );

        return http.build();
    }
}