package ru.pavlov.tech_services_app.users.mapper;

import org.mapstruct.Mapper;
import ru.pavlov.tech_services_app.users.dto.CreateUserRequestDto;
import ru.pavlov.tech_services_app.users.dto.UserResponseDto;
import ru.pavlov.tech_services_app.users.model.User;

@Mapper(componentModel = "spring")
public interface UserMapper {
    UserResponseDto toDto(User user);

    User toEntity(CreateUserRequestDto createUserRequestDto);
}
