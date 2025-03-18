package ru.pavlov.tech_services_app.bookings.dto;

import ru.pavlov.tech_services_app.services.dto.ServiceResponseDto;
import ru.pavlov.tech_services_app.users.dto.UserResponseDto;

import java.time.LocalDateTime;

public record BookingResponseDto(
        Long id,
        ServiceResponseDto service,
        LocalDateTime time,
        UserResponseDto customer,
        String comment
) {
}
