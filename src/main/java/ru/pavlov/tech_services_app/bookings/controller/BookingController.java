package ru.pavlov.tech_services_app.bookings.controller;

import jakarta.validation.Valid;
import jakarta.validation.constraints.Positive;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import ru.pavlov.tech_services_app.bookings.dto.BookingResponseDto;
import ru.pavlov.tech_services_app.bookings.dto.UpdateBookingRequestDto;
import ru.pavlov.tech_services_app.bookings.service.BookingService;
import ru.pavlov.tech_services_app.users.constants.Users;

import java.util.List;

@Slf4j
@Validated
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/bookings")
public class BookingController {

    private final BookingService bookingService;

    @DeleteMapping
    public ResponseEntity<?> deleteBooking(@RequestHeader(Users.USER_ID_HEADER) @Positive Long userId,
                                           @PathVariable @Positive Long id) {
        bookingService.cancelBooking(userId, id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PutMapping("/{id}")
    public BookingResponseDto updateBooking(@RequestHeader(Users.USER_ID_HEADER) @Positive Long userId,
                                            @PathVariable @Positive Long id,
                                            @Valid @RequestBody UpdateBookingRequestDto updateBookingRequestDto) {
        return bookingService.updateBooking(userId, id, updateBookingRequestDto);
    }

    @GetMapping("/{id}")
    public BookingResponseDto getMyBooking(@RequestHeader(Users.USER_ID_HEADER) @Positive Long userId,
                                           @PathVariable @Positive Long id) {
        return bookingService.getMyBooking(userId, id);
    }

    @GetMapping("/active")
    public List<BookingResponseDto> getMyActiveBookings(@RequestHeader(Users.USER_ID_HEADER) @Positive Long userId) {
        return bookingService.getMyActiveBookings(userId);
    }

    @GetMapping("/completed")
    public List<BookingResponseDto> getMyCompletedBookings(@RequestHeader(Users.USER_ID_HEADER) @Positive Long userId) {
        return bookingService.getMyCompletedBookings(userId);
    }
}
