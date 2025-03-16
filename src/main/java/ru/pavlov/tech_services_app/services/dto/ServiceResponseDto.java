package ru.pavlov.tech_services_app.services.dto;

import jakarta.validation.constraints.*;
import ru.pavlov.tech_services_app.services.constants.ServiceType;

public record ServiceResponseDto(
        @NotNull
        @PositiveOrZero
        Long id,

        @NotNull
        @NotBlank
        @NotEmpty
        String name,

        @NotNull
        @NotBlank
        @NotEmpty
        String description,

        @NotNull
        ServiceType type,

        @NotNull
        @Positive
        Double price
) {}
