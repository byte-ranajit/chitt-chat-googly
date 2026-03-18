package com.chatapp.iservices;

import com.chatapp.dto.LoginRequest;
import com.chatapp.dto.LoginResponse;

public interface IAuthService {
    LoginResponse register(LoginRequest request);
    LoginResponse login(LoginRequest request);
    boolean validateToken(String token);
    String getUsernameFromToken(String token);
    LoginResponse refreshToken(String token);
}
