package service;

import dto.user.UserRegistrationRequestDto;
import dto.user.UserResponseDto;

public interface UserService {
    UserResponseDto register(UserRegistrationRequestDto requestDto);
}
