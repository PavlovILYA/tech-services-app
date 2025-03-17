package ru.pavlov.tech_services_app.services.validation;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import ru.pavlov.tech_services_app.services.dto.UpdateServiceRequestDto;

public class AtLeastOneFieldNotNullValidator
        implements ConstraintValidator<AtLeastOneFieldNotNull, UpdateServiceRequestDto> {
    @Override
    public boolean isValid(UpdateServiceRequestDto dto, ConstraintValidatorContext context) {
        boolean result = dto.name() != null || dto.description() != null || dto.price() != null || dto.type() != null;
        if (!result) {
            return false;
        }
        if (dto.name() != null && dto.name().isBlank()) {
            return false;
        }
        if (dto.description() != null && dto.description().isBlank()) {
            return false;
        }
        return dto.price() == null || dto.price() >= 0;
    }
}
