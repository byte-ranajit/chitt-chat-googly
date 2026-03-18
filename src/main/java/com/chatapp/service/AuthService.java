package com.chatapp.service;

import com.chatapp.dto.LoginRequest;
import com.chatapp.dto.LoginResponse;
import com.chatapp.helper.LoginResponseHelper;
import com.chatapp.iservices.IAuthService;
import com.chatapp.model.User;
import com.chatapp.repository.UserRepository;
import com.chatapp.security.JwtUtil;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import lombok.extern.java.Log;
import org.springframework.boot.webmvc.autoconfigure.WebMvcProperties;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthService implements IAuthService {

    private final AuthenticationManager authenticationManager;
    private final JwtUtil jwtUtil;
    private final PasswordEncoder passwordEncoder;
    private final UserDetailsService userDetailsService;
    private final UserRepository userRepository;
    private final LoginResponseHelper responseHelper;

    public LoginResponse register(LoginRequest request){

        if (userRepository.existsByUserName(request.getUserName())){
            throw new RuntimeException("Username already exists");
        }

        User user = User.builder()
                .userName(request.getUserName())
                .password(passwordEncoder.encode(request.getPassword()))
                .build();
        userRepository.save(user);
        String token = jwtUtil.generateToken(user.getUserName());
        return responseHelper.getLoginResponse(user.getUserName(),token,"Registration successful");
    }

    public LoginResponse login(LoginRequest request){
        try {
            Authentication authentication = authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(request.getUserName(), request.getPassword())
            );

            UserDetails userDetails = (UserDetails) authentication.getPrincipal();
            String token = jwtUtil.generateToken(userDetails.getUsername());
            return responseHelper.getLoginResponse(userDetails.getUsername(),token,"Login successful");
        } catch (Exception e){
            throw new RuntimeException("Invalid username or password", e);
        }
    }

    public boolean validateToken(String token){
        return jwtUtil.validateToken(token);
    }

    public String getUsernameFromToken(String token){
        return jwtUtil.getUsernameFromToken(token);
    }

    public LoginResponse refreshToken(String token){
        if (!validateToken(token)){
            throw new RuntimeException("Invalid or expired token");
        }
        String userName = getUsernameFromToken(token);
        String newToken = jwtUtil.generateToken(userName);
        return LoginResponse.builder()
                .userName(userName)
                .token(newToken)
                .message("Token refreshed successfully")
                .build();
    }
}
