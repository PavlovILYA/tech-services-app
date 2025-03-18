package ru.pavlov.tech_services_app.users.dto;

import ru.pavlov.tech_services_app.users.constants.UserType;

public record UserResponseDto(
        Long id,
        String name,
        UserType type
) {}
