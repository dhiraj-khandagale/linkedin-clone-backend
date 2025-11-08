package com.linkedinclone.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class SecurityConfig {

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
            .csrf(csrf -> csrf.disable())  // Disable CSRF for testing
            .authorizeHttpRequests(auth -> auth
                .requestMatchers("/api/users/signup", "/api/users/all").permitAll()  // allow these APIs
                .anyRequest().authenticated()  // other requests need auth
            )
            .formLogin(form -> form.disable())  // disable default login page
            .httpBasic(basic -> basic.disable());  // disable basic auth

        return http.build();
    }
}
