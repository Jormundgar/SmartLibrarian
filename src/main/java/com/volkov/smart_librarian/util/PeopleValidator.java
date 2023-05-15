package com.volkov.smart_librarian.util;

import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

public class PeopleValidator implements Validator {
    @Override
    public boolean supports(Class<?> clazz) {
        return false;
    }

    @Override
    public void validate(Object target, Errors errors) {

    }
}
