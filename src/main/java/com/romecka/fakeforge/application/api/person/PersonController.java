package com.romecka.fakeforge.application.api.person;

import com.romecka.fakeforge.domain.person.PersonService;
import com.romecka.fakeforge.domain.user.UserAuthenticationDetails;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springdoc.api.annotations.ParameterObject;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import static org.springframework.http.HttpStatus.CREATED;
import static org.springframework.http.HttpStatus.OK;

@RestController()
@RequestMapping("/users/persons")
@RequiredArgsConstructor
public class PersonController {

    private final PersonService personService;

    @GetMapping
    @ResponseStatus(OK)
    @PreAuthorize("hasRole('USER')")
    public PersonsDto getPersonsByUser(@AuthenticationPrincipal UserAuthenticationDetails userDetails,
                                       @RequestParam(defaultValue = "0") int page,
                                       @RequestParam(defaultValue = "10") int size) {
        return PersonsDto.of(personService.getPersonsFromUser(
                userDetails.getUserId(),
                page,
                size
        ));
    }

    @PostMapping
    @ResponseStatus(CREATED)
    @PreAuthorize("hasRole('USER')")
    public PersonDto createPerson(@AuthenticationPrincipal UserAuthenticationDetails userDetails,
                                  @ParameterObject @Valid PersonRequest personRequest) {
        return PersonDto.of(personService.createPerson(userDetails.getUserId(), personRequest));
    }

}
