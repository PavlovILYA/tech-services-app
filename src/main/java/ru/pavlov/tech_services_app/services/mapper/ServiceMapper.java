package ru.pavlov.tech_services_app.services.mapper;

import org.mapstruct.BeanMapping;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValuePropertyMappingStrategy;
import ru.pavlov.tech_services_app.services.dto.CreateServiceRequestDto;
import ru.pavlov.tech_services_app.services.dto.ServiceResponseDto;
import ru.pavlov.tech_services_app.services.dto.UpdateServiceRequestDto;
import ru.pavlov.tech_services_app.services.model.ServiceModel;

@Mapper(componentModel = "spring")
public interface ServiceMapper {
    ServiceResponseDto toDto(ServiceModel serviceModel);

    ServiceModel toModel(CreateServiceRequestDto createServiceRequestDto);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    void updateServiceFromDto(UpdateServiceRequestDto dto, @MappingTarget ServiceModel entity);
}
