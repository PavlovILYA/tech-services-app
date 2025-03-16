package ru.pavlov.tech_services_app.services.validation;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.*;

@Documented
@Constraint(validatedBy = AtLeastOneFieldNotNullValidator.class)
@Target({ ElementType.TYPE })
@Retention(RetentionPolicy.RUNTIME)
public @interface AtLeastOneFieldNotNull {
    String message() default "Хотя бы одно поле должно быть заполнено";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}
