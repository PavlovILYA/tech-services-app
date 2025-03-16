package ru.pavlov.tech_services_app.services.dto;

import jakarta.annotation.Nullable;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Positive;
import ru.pavlov.tech_services_app.services.constants.ServiceType;
import ru.pavlov.tech_services_app.services.validation.AtLeastOneFieldNotNull;

@AtLeastOneFieldNotNull
public record UpdateServiceRequestDto(
        @Nullable
        @NotBlank
        @NotEmpty
        String name,

        @Nullable
        @NotBlank
        @NotEmpty
        String description,

        @Nullable
        ServiceType type,

        @Nullable
        @Positive
        Double price
) {}
