package ru.pavlov.tech_services_app.services.dto;

import jakarta.annotation.Nullable;
import ru.pavlov.tech_services_app.services.constants.ServiceType;
import ru.pavlov.tech_services_app.services.validation.AtLeastOneFieldNotNull;

@AtLeastOneFieldNotNull
public record UpdateServiceRequestDto(
        @Nullable
        String name,

        @Nullable
        String description,

        @Nullable
        ServiceType type,

        @Nullable
        Double price
) {}
