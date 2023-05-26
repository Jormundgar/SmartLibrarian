package com.volkov.smart_librarian.util;

import com.volkov.smart_librarian.models.Person;
import com.volkov.smart_librarian.services.PersonService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

@Component
@AllArgsConstructor(onConstructor = @__(@Autowired))
public class PeopleValidator implements Validator {

    private final PersonService personService;

    @Override
    public boolean supports(Class<?> sClass) {
        return Person.class.equals(sClass);
    }

    @Override
    public void validate(Object target, Errors errors) {
        var person = (Person) target;
        if(personService.findOneByName(person.getName()).isPresent()) {
            errors.rejectValue("name", "", "This user already exist");
        }
    }

    public void validateForUpdate(Object target, Errors errors) {
        var person = (Person) target;
        if(personService.findOneByNameAndYearOfBirth(person.getName(), person.getYearOfBirth()).isPresent()) {
            errors.rejectValue("name", "", "This user already exist");
            errors.rejectValue("yearOfBirth", "", "This user already exist");
        }
    }
}
