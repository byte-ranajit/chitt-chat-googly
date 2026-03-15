package com.chatapp.config;

import com.chatapp.security.JwtAuthenticationFilter;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class SecurityConfig {
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception{
        http.csrf(csrf -> csrf.disable())
                .authorizeHttpRequests( auths -> auths
                .requestMatchers("/auth/**").permitAll()
//                                .requestMatchers("/auth/login").permitAll()
//                                .requestMatchers("/auth/register").permitAll()
                .anyRequest().authenticated()
                ).addFilterBefore(jw);

        return http.build();
    }

    public JwtAuthenticationFilter jwtAuthenticationFilter(){

    }
}
