package mapper;

import config.MapperConfig;
import dto.user.UserRegistrationRequestDto;
import dto.user.UserResponseDto;
import model.User;
import org.mapstruct.Mapper;

@Mapper(config = MapperConfig.class)
public interface UserMapper {
    UserResponseDto toDto(User user);

    User toModel(UserRegistrationRequestDto userRequestDto);
}
