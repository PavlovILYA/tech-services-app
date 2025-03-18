package ru.pavlov.tech_services_app.users.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import ru.pavlov.tech_services_app.users.constants.UserType;

public record CreateUserRequestDto(
        @NotNull
        @NotBlank
        @NotEmpty
        String name,

        @NotNull
        UserType type
) {}
