package com.chatapp.controller;

import com.chatapp.dto.LoginRequest;
import com.chatapp.dto.LoginResponse;
import com.chatapp.service.AuthService;
import lombok.AllArgsConstructor;
import lombok.extern.java.Log;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
@AllArgsConstructor
public class AuthController {

    private final AuthService authService;


    @PostMapping("/login")
    public ResponseEntity<LoginResponse> login(@RequestBody LoginRequest loginRequest){
        return ResponseEntity.ok(authService.login(loginRequest));
    }

    @PostMapping("/register")
    public ResponseEntity<LoginResponse> register(@RequestBody LoginRequest register){
        return ResponseEntity.ok(authService.register(register));
    }

    @PostMapping("/refresh")
    ResponseEntity<LoginResponse> refreshToken(@RequestHeader("Authorization") String token){
        return ResponseEntity.ok(authService.refreshToken(token.substring(7)));
    }

    @PostMapping("/logout")
    public ResponseEntity<String> logout (@RequestHeader("Authorization") String token){
//        authService.logout(token.substring(7));
//        TODO :: add logout code
        return ResponseEntity.ok("Logged out successfull");
    }

    @GetMapping("/validate")
    public ResponseEntity<Boolean> validateToken (@RequestHeader("Authorization") String token){
        return ResponseEntity.ok(authService.validateToken(token.substring(7)));
    }
}
