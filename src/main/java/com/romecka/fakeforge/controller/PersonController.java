package com.romecka.fakeforge.controller;

import com.romecka.fakeforge.annotation.CurrentUser;
import com.romecka.fakeforge.domain.dto.PersonDto;
import com.romecka.fakeforge.domain.entities.User;
import com.romecka.fakeforge.facade.PersonFacade;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

import static org.springframework.http.HttpStatus.OK;

@RestController
@RequiredArgsConstructor
public class PersonController {

    private final PersonFacade personFacade;

    @GetMapping(value = "/users/persons")
    @ResponseStatus(OK)
    public List<PersonDto> getPersonsByUser(@CurrentUser User user,
                                            @RequestParam(defaultValue = "0") int page,
                                            @RequestParam(defaultValue = "10") int size) {
        return personFacade.getPersonsByUserId(user.getId(), page, size);
    }

}
