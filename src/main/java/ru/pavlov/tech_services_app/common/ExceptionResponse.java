package ru.pavlov.tech_services_app.common;

import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@Builder
public class ExceptionResponse {
    private String status;
    private String reason;
    private String message;
    private List<String> errors;
    private String timestamp;
}
