package ru.pavlov.tech_services_app.services.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.pavlov.tech_services_app.services.model.ServiceModel;

public interface ServiceRepository extends JpaRepository<ServiceModel, Long> {
}
