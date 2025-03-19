package ru.pavlov.tech_services_app.bookings.exception;

public class BookingAccessException extends RuntimeException {
    public BookingAccessException(Long userId, Long bookingId) {
        super("User " + userId + " doesn't have access to booking " + bookingId);
    }
}
