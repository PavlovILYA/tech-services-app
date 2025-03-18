package ru.pavlov.tech_services_app.services.mapper;

import org.mapstruct.*;
import ru.pavlov.tech_services_app.services.dto.CreateServiceRequestDto;
import ru.pavlov.tech_services_app.services.dto.ServiceResponseDto;
import ru.pavlov.tech_services_app.services.dto.UpdateServiceRequestDto;
import ru.pavlov.tech_services_app.services.model.ServiceModel;
import ru.pavlov.tech_services_app.users.model.User;

@Mapper(componentModel = "spring")
public interface ServiceMapper {
    ServiceResponseDto toDto(ServiceModel serviceModel);

    @Mapping(target = "provider", source = "user")
    @Mapping(target = "id", ignore = true)
    @Mapping(target = "name", source = "createServiceRequestDto.name")
    @Mapping(target = "type", source = "createServiceRequestDto.type")
    ServiceModel toEntity(CreateServiceRequestDto createServiceRequestDto, User user);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    void updateServiceFromDto(UpdateServiceRequestDto dto, @MappingTarget ServiceModel entity);
}
