package ru.pavlov.tech_services_app.bookings.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import ru.pavlov.tech_services_app.bookings.dto.BookingResponseDto;
import ru.pavlov.tech_services_app.bookings.dto.UpdateBookingRequestDto;
import ru.pavlov.tech_services_app.bookings.exception.BookingAccessException;
import ru.pavlov.tech_services_app.bookings.exception.BookingNotFoundException;
import ru.pavlov.tech_services_app.bookings.mapper.BookingMapper;
import ru.pavlov.tech_services_app.bookings.model.Booking;
import ru.pavlov.tech_services_app.bookings.repository.BookingRepository;
import ru.pavlov.tech_services_app.services.exception.ServiceNotFoundException;
import ru.pavlov.tech_services_app.services.model.ServiceModel;
import ru.pavlov.tech_services_app.services.repository.ServiceRepository;
import ru.pavlov.tech_services_app.users.exception.UserNotFoundException;
import ru.pavlov.tech_services_app.users.model.User;
import ru.pavlov.tech_services_app.users.repository.UserRepository;

import java.time.LocalDateTime;
import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class BookingService {

    private final BookingRepository bookingRepository;
    private final BookingMapper bookingMapper;
    private final UserRepository userRepository;
    private final ServiceRepository serviceRepository;

    public BookingResponseDto createBooking(Long userId, Long serviceId, LocalDateTime time, String comment) {
        User user = userRepository.findById(userId).orElseThrow(() -> new UserNotFoundException(userId));
        ServiceModel service = serviceRepository.findById(serviceId)
                .orElseThrow(() -> new ServiceNotFoundException(serviceId));
        if (user.getId().equals(service.getProvider().getId())) {
            throw new RuntimeException("cannot book own service");
        }
        Booking booking = bookingMapper.toEntity(time, comment, service, user);
        booking = bookingRepository.save(booking);
        return bookingMapper.toDto(booking);
    }

    public void cancelBooking(Long userId, Long id) {
        Booking booking = getMyBookingModel(userId, id);
        bookingRepository.delete(booking);
    }

    public BookingResponseDto updateBooking(Long userId, Long id, UpdateBookingRequestDto updateBookingRequestDto) {
        Booking booking = getMyBookingModel(userId, id);
        bookingMapper.updateBookingFromDto(updateBookingRequestDto, booking);
        booking = bookingRepository.save(booking);
        return bookingMapper.toDto(booking);
    }

    public BookingResponseDto getMyBooking(Long userId, Long id) {
        Booking booking = getMyBookingModel(userId, id);
        return bookingMapper.toDto(booking);
    }

    public List<BookingResponseDto> getMyActiveBookings(Long userId) {
        return bookingRepository.findByCustomerIdAndTimeAfter(userId, LocalDateTime.now()).stream()
                .map(bookingMapper::toDto)
                .toList();
    }

    public List<BookingResponseDto> getMyCompletedBookings(Long userId) {
        return bookingRepository.findByCustomerIdAndTimeBefore(userId, LocalDateTime.now()).stream()
                .map(bookingMapper::toDto)
                .toList();
    }

    private Booking getMyBookingModel(Long userId, Long id) {
        User user = userRepository.findById(userId).orElseThrow(() -> new UserNotFoundException(userId));
        Booking booking = bookingRepository.findById(id).orElseThrow(() -> new BookingNotFoundException(id));
        checkAccess(user, booking);
        return booking;
    }

    private void checkAccess(User user, Booking booking) {
        if (!user.getId().equals(booking.getCustomer().getId())) {
            throw new BookingAccessException(user.getId(), booking.getId());
        }
    }
}
