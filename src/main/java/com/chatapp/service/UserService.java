package com.chatapp.service;

import com.chatapp.dto.UserDto;
import com.chatapp.iservices.IUserService;
import com.chatapp.repository.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class UserService implements IUserService {
    private UserRepository userRepository;
    @Override
    public List<UserDto> getAllUsers(){
        return userRepository.findAll()
                .stream()
                .map(user -> new UserDto(
                        user.getId(),
                        user.getUserName()
                )).collect(Collectors.toList());
    }
}
