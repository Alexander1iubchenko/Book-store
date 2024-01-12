package com.example.application.mapper;

import com.example.application.config.MapperConfig;
import com.example.application.dto.user.UserRegistrationRequestDto;
import com.example.application.dto.user.UserResponseDto;
import com.example.application.model.User;
import org.mapstruct.Mapper;

@Mapper(config = MapperConfig.class)
public interface UserMapper {
    UserResponseDto toDto(User user);

    User toModel(UserRegistrationRequestDto userRequestDto);
}
