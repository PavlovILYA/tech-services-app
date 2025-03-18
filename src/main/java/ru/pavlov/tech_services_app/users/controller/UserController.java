package ru.pavlov.tech_services_app.users.controller;

import jakarta.validation.Valid;
import jakarta.validation.constraints.Positive;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import ru.pavlov.tech_services_app.services.dto.CreateServiceRequestDto;
import ru.pavlov.tech_services_app.services.dto.ServiceResponseDto;
import ru.pavlov.tech_services_app.services.dto.UpdateServiceRequestDto;
import ru.pavlov.tech_services_app.users.dto.CreateUserRequestDto;
import ru.pavlov.tech_services_app.users.dto.UserResponseDto;
import ru.pavlov.tech_services_app.users.service.UserService;

import java.util.List;

@Slf4j
@Validated
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/users")
public class UserController {

    private final UserService userService;

    @PostMapping
    public ResponseEntity<UserResponseDto> createUser(@Valid @RequestBody CreateUserRequestDto createUserRequestDto) {
        return new ResponseEntity<>(userService.createUser(createUserRequestDto), HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    public UserResponseDto getUser(@PathVariable @Positive Long id) {
        return userService.getUser(id);
    }

    @GetMapping
    public List<UserResponseDto> getUsers() {
        return userService.getUsers();
    }
}
