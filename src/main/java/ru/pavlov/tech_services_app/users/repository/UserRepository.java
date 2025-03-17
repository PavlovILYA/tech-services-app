package ru.pavlov.tech_services_app.users.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.pavlov.tech_services_app.users.model.User;

public interface UserRepository extends JpaRepository<User, Long> {
}
