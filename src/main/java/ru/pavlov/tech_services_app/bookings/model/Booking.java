package ru.pavlov.tech_services_app.bookings.model;

import jakarta.persistence.*;
import lombok.Data;
import ru.pavlov.tech_services_app.services.model.ServiceModel;
import ru.pavlov.tech_services_app.users.model.User;

import java.time.LocalDateTime;

@Data
@Entity
@Table(name = "bookings", schema = "public")
public class Booking {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "time")
    private LocalDateTime time;

    @Column(name = "comment")
    private String comment;

    @ManyToOne
    @JoinColumn(name = "service_id", nullable = false)
    private ServiceModel service;

    @ManyToOne
    @JoinColumn(name = "customer_id", nullable = false)
    private User customer;
}
