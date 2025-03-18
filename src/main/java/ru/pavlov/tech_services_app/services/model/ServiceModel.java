package ru.pavlov.tech_services_app.services.model;

import jakarta.persistence.*;
import lombok.*;
import ru.pavlov.tech_services_app.services.constants.ServiceType;
import ru.pavlov.tech_services_app.users.model.User;

@Data
@Entity
@Table(name = "services", schema = "public")
public class ServiceModel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "name")
    private String name;

    @Column(name = "description")
    private String description;

    @Enumerated(value = EnumType.STRING)
    @Column(name = "type")
    private ServiceType type;

    @Column(name = "price")
    private Double price;

    @ManyToOne
    @JoinColumn(name = "provider_id", nullable = false)
    private User provider;
}
