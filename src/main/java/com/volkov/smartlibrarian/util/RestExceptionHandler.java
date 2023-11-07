package com.volkov.smartlibrarian.util;

import com.volkov.smartlibrarian.dto.ErrorDTO;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import static org.springframework.http.HttpStatus.BAD_REQUEST;

@ControllerAdvice
public class RestExceptionHandler {

    @ExceptionHandler(DataIntegrityViolationException.class)
    protected ResponseEntity<Object> handleDataIntegrityViolation(DataIntegrityViolationException ex) {
        var errorDTO = new ErrorDTO(BAD_REQUEST);
        errorDTO.setMessage("The record with this name already exists");
        errorDTO.setDebugMessage(ex.getMostSpecificCause().getMessage());
        return buildResponseEntity(errorDTO);
    }

    private ResponseEntity<Object> buildResponseEntity(ErrorDTO errorDTO) {
        return new ResponseEntity<>(errorDTO, errorDTO.getStatus());
    }

}
