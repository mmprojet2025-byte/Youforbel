package be.iccbxl.pid.youforbel.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import be.iccbxl.pid.youforbel.model.User;
import be.iccbxl.pid.youforbel.repository.UserRepository;
import be.iccbxl.pid.youforbel.security.JwtAuthenticationFilter;

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
    public SecurityFilterChain filterChain(
            HttpSecurity http,
            JwtAuthenticationFilter jwtAuthenticationFilter) throws Exception {

        http
            // Désactiver CSRF pour API REST
            .csrf(csrf -> csrf
                .ignoringRequestMatchers("/api/**")
            )

            // JWT = stateless
            .sessionManagement(session -> session
                .sessionCreationPolicy(SessionCreationPolicy.STATELESS)
            )

            .authorizeHttpRequests(auth -> auth

                // Pages publiques
                .requestMatchers(
                    "/",
                    "/login",
                    "/register",
                    "/css/**",
                    "/js/**",
                    "/images/**",
                    "/uploads/**",
                    "/api/auth/**",
                    "/swagger-ui/**",
                    "/swagger-ui.html",
                    "/v3/api-docs/**",
                    "/error/**"
                ).permitAll()

                // Autoriser OPTIONS pour CORS
                .requestMatchers(
                    HttpMethod.OPTIONS,
                    "/api/**"
                ).permitAll()

                // API sécurisée JWT
                .requestMatchers("/api/**").authenticated()

                // ADMIN seulement
                .requestMatchers("/admin").hasRole("ADMIN")
                .requestMatchers("/artists/new").hasRole("ADMIN")
                .requestMatchers("/artists/*/edit").hasRole("ADMIN")
                .requestMatchers("/artists/*/delete").hasRole("ADMIN")

                // Utilisateurs connectés
                .requestMatchers("/artists", "/artists/*").authenticated()
                .requestMatchers("/profile").authenticated()

                // Tout le reste
                .anyRequest().permitAll()
            )

            .formLogin(form -> form
                .loginPage("/login")
                .defaultSuccessUrl("/artists", true)
                .permitAll()
            )

            .logout(logout -> logout
                .logoutSuccessUrl("/login?logout")
                .permitAll()
            )

            .exceptionHandling(exception -> exception
                .accessDeniedPage("/error/403")
            )

            // JWT Filter
            .addFilterBefore(
                jwtAuthenticationFilter,
                UsernamePasswordAuthenticationFilter.class
            );

        return http.build();
    }
}
