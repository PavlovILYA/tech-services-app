package ru.pavlov.tech_services_app.bookings.dto;

import jakarta.validation.constraints.Future;

import java.time.LocalDateTime;

public record UpdateBookingRequestDto(
        @Future
        LocalDateTime time,
        String comment
) {
}
