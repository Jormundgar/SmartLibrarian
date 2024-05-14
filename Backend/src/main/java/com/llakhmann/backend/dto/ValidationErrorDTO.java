package com.llakhmann.backend.dto;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@EqualsAndHashCode(callSuper = false)
@AllArgsConstructor
public class ValidationErrorDTO {

    private String object;
    private String field;
    private Object rejectedValue;
    private String message;
}
