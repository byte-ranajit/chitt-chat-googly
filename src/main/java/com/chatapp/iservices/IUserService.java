package com.chatapp.iservices;

import com.chatapp.dto.UserDto;

import java.util.List;

public interface IUserService {
    List<UserDto> getAllUsers();
}
