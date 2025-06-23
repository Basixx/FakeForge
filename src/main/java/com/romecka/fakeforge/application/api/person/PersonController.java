package com.romecka.fakeforge.application.api.person;

import com.romecka.fakeforge.application.config.CurrentUser;
import com.romecka.fakeforge.domain.person.PersonService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import static org.springframework.http.HttpStatus.OK;

@RestController
@RequiredArgsConstructor
public class PersonController {

    private final PersonService personService;

    @GetMapping(value = "/users/persons")
    @ResponseStatus(OK)
    public PersonResponse getPersonsByUser(@CurrentUser Long userId,
                                           @RequestParam(defaultValue = "0") int page,
                                           @RequestParam(defaultValue = "10") int size) {
        return PersonResponse.fromPerson(personService.getPersonsFromUser(userId, page, size));
    }

}
