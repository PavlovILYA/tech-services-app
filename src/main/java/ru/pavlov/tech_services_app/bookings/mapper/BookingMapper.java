package ru.pavlov.tech_services_app.bookings.mapper;

import org.mapstruct.*;
import ru.pavlov.tech_services_app.bookings.dto.BookingResponseDto;
import ru.pavlov.tech_services_app.bookings.dto.UpdateBookingRequestDto;
import ru.pavlov.tech_services_app.bookings.model.Booking;
import ru.pavlov.tech_services_app.services.model.ServiceModel;
import ru.pavlov.tech_services_app.users.model.User;

import java.time.LocalDateTime;

@Mapper(componentModel = "spring")
public interface BookingMapper {

    BookingResponseDto toDto(Booking booking);

    @Mapping(target = "id", ignore = true)
    Booking toEntity(LocalDateTime time, String comment, ServiceModel service, User customer);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    void updateBookingFromDto(UpdateBookingRequestDto dto, @MappingTarget Booking booking);
}
