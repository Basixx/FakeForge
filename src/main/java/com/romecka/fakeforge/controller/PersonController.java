package com.romecka.fakeforge.controller;

import com.romecka.fakeforge.domain.person.PersonDto;
import com.romecka.fakeforge.facade.PersonFacade;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class PersonController {

    private final PersonFacade personFacade;

    @GetMapping(value = "/users/{userId}/persons")
    @ResponseStatus(HttpStatus.OK)
    public List<PersonDto> getPersonsByUser(@PathVariable Long userId,
                                            @RequestParam(defaultValue = "0") int page,
                                            @RequestParam(defaultValue = "10") int size) {
        return personFacade.getPersonsByUserId(userId, page, size);
    }
}
