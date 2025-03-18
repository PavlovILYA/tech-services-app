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
import ru.pavlov.tech_services_app.users.repository.UserRepository;

import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class ServiceService {

    private final ServiceRepository serviceRepository;
    private final ServiceMapper serviceMapper;
    private final UserRepository userRepository;

    public ServiceResponseDto createService(Long userId, CreateServiceRequestDto createServiceDto) {
        ServiceModel service = serviceMapper.toModel(createServiceDto, userRepository.findById(userId).get());
        service = serviceRepository.save(service);
        return serviceMapper.toDto(service);
    }

    public ServiceResponseDto updateService(Long userId, Long id, UpdateServiceRequestDto updateServiceDto) {
        ServiceModel service = serviceRepository.findById(id).get();
        if (!service.getProvider().getId().equals(userId)) {
            throw new RuntimeException("403");
        }
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
                .toList();
    }


}
