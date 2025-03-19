package ru.pavlov.tech_services_app.services.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import ru.pavlov.tech_services_app.services.dto.CreateServiceRequestDto;
import ru.pavlov.tech_services_app.services.dto.ServiceResponseDto;
import ru.pavlov.tech_services_app.services.dto.UpdateServiceRequestDto;
import ru.pavlov.tech_services_app.services.exception.ServiceAccessException;
import ru.pavlov.tech_services_app.services.exception.ServiceNotFoundException;
import ru.pavlov.tech_services_app.services.mapper.ServiceMapper;
import ru.pavlov.tech_services_app.services.model.ServiceModel;
import ru.pavlov.tech_services_app.services.repository.ServiceRepository;
import ru.pavlov.tech_services_app.users.exception.UserNotFoundException;
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
        ServiceModel service = serviceMapper.toEntity(createServiceDto,
                userRepository.findById(userId).orElseThrow(() -> new UserNotFoundException(userId)));
        service = serviceRepository.save(service);
        return serviceMapper.toDto(service);
    }

    public ServiceResponseDto updateService(Long userId, Long id, UpdateServiceRequestDto updateServiceDto) {
        ServiceModel service = serviceRepository.findById(id).orElseThrow(() -> new ServiceNotFoundException(id));
        if (!service.getProvider().getId().equals(userId)) {
            throw new ServiceAccessException(userId, id);
        }
        serviceMapper.updateServiceFromDto(updateServiceDto, service);
        service = serviceRepository.save(service);
        return serviceMapper.toDto(service);
    }

    public ServiceResponseDto getService(Long id) {
        ServiceModel service = serviceRepository.findById(id).orElseThrow(() -> new ServiceNotFoundException(id));
        return serviceMapper.toDto(service);
    }

    public List<ServiceResponseDto> getServices() {
        return serviceRepository.findAll().stream()
                .map(serviceMapper::toDto)
                .toList();
    }


}
