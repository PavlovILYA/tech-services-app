package ru.pavlov.tech_services_app.services.exception;

public class ServiceAccessException extends RuntimeException {
  public ServiceAccessException(Long userId, Long serviceId) {
    super("User " + userId + " doesn't have access to service " + serviceId);
  }
}
