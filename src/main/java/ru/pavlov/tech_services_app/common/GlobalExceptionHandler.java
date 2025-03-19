package ru.pavlov.tech_services_app.common;

import lombok.extern.slf4j.Slf4j;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import ru.pavlov.tech_services_app.bookings.exception.BookingAccessException;
import ru.pavlov.tech_services_app.bookings.exception.BookingNotFoundException;
import ru.pavlov.tech_services_app.services.exception.ServiceAccessException;
import ru.pavlov.tech_services_app.services.exception.ServiceNotFoundException;
import ru.pavlov.tech_services_app.users.exception.UserNotFoundException;

import java.time.LocalDateTime;

import static ru.pavlov.tech_services_app.common.Constants.formatter;

@Slf4j
@RestControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler({UserNotFoundException.class,
                       ServiceNotFoundException.class,
                       BookingNotFoundException.class})
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ExceptionResponse handle404Exception(final Exception e) {
        log.error("{} {}", HttpStatus.NOT_FOUND, e.getMessage());
        return ExceptionResponse.builder()
                .status(HttpStatus.NOT_FOUND.name())
                .reason("The required object was not found.")
                .message(e.getMessage())
                .timestamp(LocalDateTime.now().format(formatter))
                .build();
    }

    @ExceptionHandler({ServiceAccessException.class,
                       BookingAccessException.class})
    @ResponseStatus(HttpStatus.FORBIDDEN)
    public ExceptionResponse handle403Exception(final Exception e) {
        log.error("{} {}", HttpStatus.FORBIDDEN, e.getMessage());
        return ExceptionResponse.builder()
                .status(HttpStatus.FORBIDDEN.name())
                .reason("The client does not have permission to access the requested resource.")
                .message(e.getMessage())
                .timestamp(LocalDateTime.now().format(formatter))
                .build();
    }

    @ExceptionHandler({MissingServletRequestParameterException.class,
                       MethodArgumentNotValidException.class,
                       org.hibernate.exception.ConstraintViolationException.class,
                       DataIntegrityViolationException.class})
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ExceptionResponse handle400Exception(final Exception e) {
        log.error("{} {}", HttpStatus.BAD_REQUEST, e.getMessage());
        return ExceptionResponse.builder()
                .status(HttpStatus.BAD_REQUEST.name())
                .reason("For the requested operation the conditions are not met.")
                .message(e.getMessage())
                .timestamp(LocalDateTime.now().format(formatter))
                .build();
    }

    @ExceptionHandler
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public ExceptionResponse handle500Exception(final Exception e) {
        log.error("{} {}", HttpStatus.INTERNAL_SERVER_ERROR, e.getMessage());
        return ExceptionResponse.builder()
                .status(HttpStatus.INTERNAL_SERVER_ERROR.name())
                .reason("Error occurred")
                .message(e.getMessage())
                .timestamp(LocalDateTime.now().format(formatter))
                .build();
    }
}
