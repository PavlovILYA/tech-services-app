package ru.pavlov.tech_services_app.services.exception;

public class ServiceNotFoundException extends RuntimeException {
  public ServiceNotFoundException(Long serviceId) {
    super("Service " + serviceId + " not found");
  }
}
