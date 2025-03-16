package ru.pavlov.tech_services_app.services.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import ru.pavlov.tech_services_app.services.dto.CreateServiceRequestDto;
import ru.pavlov.tech_services_app.services.dto.ServiceResponseDto;
import ru.pavlov.tech_services_app.services.dto.UpdateServiceRequestDto;
import ru.pavlov.tech_services_app.services.mapper.ServiceMapper;
import ru.pavlov.tech_services_app.services.model.ServiceModel;
import ru.pavlov.tech_services_app.services.repository.ServiceRepository;

import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@Service
@RequiredArgsConstructor
public class ServiceService {

    private final ServiceRepository serviceRepository;
    private final ServiceMapper serviceMapper;

    public ServiceResponseDto createService(CreateServiceRequestDto createServiceDto) {
        ServiceModel service = serviceMapper.toModel(createServiceDto);
        service = serviceRepository.save(service);
        return serviceMapper.toDto(service);
    }

    public ServiceResponseDto updateService(Long id, UpdateServiceRequestDto updateServiceDto) {
        ServiceModel service = serviceRepository.findById(id).get();
        serviceMapper.updateServiceFromDto(updateServiceDto, service);
        service = serviceRepository.save(service);
        return serviceMapper.toDto(service);
    }

    public ServiceResponseDto getService(Long id) {
        ServiceModel service = serviceRepository.findById(id).get();
        return serviceMapper.toDto(service);
    }

    public List<ServiceResponseDto> getServices() {
        return serviceRepository.findAll().stream()
                .map(serviceMapper::toDto)
                .collect(Collectors.toList());
    }
}
