package ru.pavlov.tech_services_app.users.exception;

public class UserNotFoundException extends RuntimeException {
    public UserNotFoundException(Long userId) {
        super("User " + userId + " not found!");
    }
}
