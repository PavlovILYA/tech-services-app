package ru.pavlov.tech_services_app.bookings.exception;

public class BookingNotFoundException extends RuntimeException {
    public BookingNotFoundException(Long id) {
        super("Booking " + id + " not found");
    }
}
