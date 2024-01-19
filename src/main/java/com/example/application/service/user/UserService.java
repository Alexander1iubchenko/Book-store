package com.example.application.service.user;

import com.example.application.dto.user.UserRegistrationRequestDto;
import com.example.application.dto.user.UserResponseDto;

public interface UserService {
    UserResponseDto register(UserRegistrationRequestDto requestDto);
}
