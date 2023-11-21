package com.volkov.backend.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.validation.ConstraintViolation;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.validator.internal.engine.path.PathImpl;
import org.springframework.http.HttpStatus;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

@Getter
@Setter
public class ErrorDTO {

    private HttpStatus status;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy hh:mm:ss")
    private LocalDateTime timestamp;
    private String message;
    private String debugMessage;
    private List<ValidationErrorDTO> validationErrors;

    private ErrorDTO() {
        timestamp = LocalDateTime.now();
    }

    public ErrorDTO(HttpStatus status) {
        this();
        this.status = status;
    }

    private void addValidationError(ValidationErrorDTO validationError) {
        if (validationErrors == null) {
            validationErrors = new ArrayList<>();
        }
        validationErrors.add(validationError);
    }

    private void addValidationError(String object, String field, Object rejectedValue, String message) {
        addValidationError(new ValidationErrorDTO(object, field, rejectedValue, message));
    }

    private void addValidationError(ConstraintViolation<?> cv) {
        this.addValidationError(
                cv.getRootBeanClass().getSimpleName(),
                ((PathImpl) cv.getPropertyPath()).getLeafNode().asString(),
                cv.getInvalidValue(),
                cv.getMessage());
    }

    public void addValidationErrors(Set<ConstraintViolation<?>> constraintViolations) {
        constraintViolations.forEach(this::addValidationError);
    }
}
