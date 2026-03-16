package com.chatapp.helper;

import com.chatapp.dto.LoginResponse;
import org.springframework.stereotype.Component;

@Component
public class LoginResponseHelper {
    public LoginResponse getLoginResponse(String userName, String token, String message){
        return LoginResponse.builder()
                .userName(userName)
                .token(token)
                .message(message)
                .build();
    }
}