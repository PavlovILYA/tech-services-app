package ru.pavlov.tech_services_app.bookings.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.pavlov.tech_services_app.bookings.model.Booking;

import java.time.LocalDateTime;
import java.util.List;

public interface BookingRepository extends JpaRepository<Booking, Long> {
    List<Booking> findByCustomerIdAndTimeAfter(Long customerId, LocalDateTime time);
    List<Booking> findByCustomerIdAndTimeBefore(Long customerId, LocalDateTime time);
}
