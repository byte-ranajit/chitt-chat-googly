package com.chatapp.service;

import com.chatapp.model.User;
import com.chatapp.repository.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.boot.webmvc.autoconfigure.WebMvcProperties;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class AuthService {
    private final UserRepository userRepository;

    public User register(User user){
        return userRepository.save(user);
    }

    public User login(String username, String password){
        User user = userRepository.findByUsername(username);
        if (user != null && user.getPassword().equals(password))
            return user;

        return null;
    }
}
