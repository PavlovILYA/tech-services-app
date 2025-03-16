package ru.pavlov.tech_services_app.services.validation;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import ru.pavlov.tech_services_app.services.dto.UpdateServiceRequestDto;

public class AtLeastOneFieldNotNullValidator
        implements ConstraintValidator<AtLeastOneFieldNotNull, UpdateServiceRequestDto> {
    @Override
    public boolean isValid(UpdateServiceRequestDto dto, ConstraintValidatorContext context) {
        return dto.name() != null || dto.description() != null || dto.price() != null || dto.type() != null;
    }
}
