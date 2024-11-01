package com.banco.cuentas_bancarias.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                .csrf(AbstractHttpConfigurer::disable)// Desactivar CSRF para facilitar pruebas con Postman
                .authorizeHttpRequests(auth -> auth
                .requestMatchers("/api/clientes").hasRole("ADMIN") // Rutas públicas
                .requestMatchers("/api/clientes/{id}").hasAnyRole("ADMIN", "USER") // Rutas públicas
                .anyRequest().authenticated() // Cualquier otra ruta requiere autenticación
                )
                .httpBasic(Customizer.withDefaults()); //Usa autenticación básica (user / password)

        return http.build();

    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}
