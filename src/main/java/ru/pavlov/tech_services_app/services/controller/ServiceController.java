package ru.pavlov.tech_services_app.services.controller;

import jakarta.validation.Valid;
import jakarta.validation.constraints.Future;
import jakarta.validation.constraints.Positive;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import ru.pavlov.tech_services_app.bookings.dto.BookingResponseDto;
import ru.pavlov.tech_services_app.bookings.service.BookingService;
import ru.pavlov.tech_services_app.services.dto.CreateServiceRequestDto;
import ru.pavlov.tech_services_app.services.dto.ServiceResponseDto;
import ru.pavlov.tech_services_app.services.dto.UpdateServiceRequestDto;
import ru.pavlov.tech_services_app.services.service.ServiceService;
import ru.pavlov.tech_services_app.users.constants.Users;

import java.time.LocalDateTime;
import java.util.List;

@Slf4j
@Validated
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/services")
public class ServiceController {

    private final ServiceService serviceService;
    private final BookingService bookingService;

    @PostMapping
    public ResponseEntity<ServiceResponseDto> createService(@RequestHeader(Users.USER_ID_HEADER) @Positive Long userId,
                                                            @Valid @RequestBody CreateServiceRequestDto createServiceDto) {
        return new ResponseEntity<>(serviceService.createService(userId, createServiceDto), HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ServiceResponseDto updateService(@RequestHeader(Users.USER_ID_HEADER) @Positive Long userId,
                                            @PathVariable @Positive Long id,
                                            @Valid @RequestBody UpdateServiceRequestDto updateServiceDto) {
        return serviceService.updateService(userId, id, updateServiceDto);
    }

    @GetMapping("/{id}")
    public ServiceResponseDto getService(@PathVariable @Positive Long id) {
        return serviceService.getService(id);
    }

    @GetMapping
    public List<ServiceResponseDto> getServices() {
        return serviceService.getServices();
    }

    @PostMapping("/{id}/book")
    public ResponseEntity<BookingResponseDto> bookService(@RequestHeader(Users.USER_ID_HEADER) @Positive
                                                          Long userId,
                                                          @PathVariable @Positive
                                                          Long id,
                                                          @RequestParam(name = "time") @Future
                                                          LocalDateTime time,
                                                          @RequestParam(name = "comment", required = false)
                                                          String comment) {
        return new ResponseEntity<>(bookingService.createBooking(userId, id, time, comment), HttpStatus.CREATED);
    }
}
