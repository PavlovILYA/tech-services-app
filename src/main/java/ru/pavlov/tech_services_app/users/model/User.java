package ru.pavlov.tech_services_app.users.model;

import jakarta.persistence.*;
import lombok.Data;
import ru.pavlov.tech_services_app.users.constants.UserType;

@Data
@Entity
@Table(name = "users", schema = "public")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "name")
    private String name;

    @Enumerated(value = EnumType.STRING)
    @Column(name = "type")
    private UserType type;
}
