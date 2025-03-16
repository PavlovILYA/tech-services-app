package ru.pavlov.tech_services_app.services.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import ru.pavlov.tech_services_app.services.constants.ServiceType;

@Getter
@Setter
@Entity
@Table(name = "service")
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
}
