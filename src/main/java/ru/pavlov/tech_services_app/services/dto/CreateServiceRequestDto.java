package ru.pavlov.tech_services_app.services.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import ru.pavlov.tech_services_app.services.constants.ServiceType;

public record CreateServiceRequestDto(
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
