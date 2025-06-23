package com.romecka.fakeforge.application.api.person;

import com.romecka.fakeforge.application.config.CurrentUser;
import com.romecka.fakeforge.domain.person.PersonFacade;
import com.romecka.fakeforge.infrastructure.db.user.User;
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
