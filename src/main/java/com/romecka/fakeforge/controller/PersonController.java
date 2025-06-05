package com.romecka.fakeforge.controller;

import com.romecka.fakeforge.domain.dto.PersonDto;
import com.romecka.fakeforge.facade.PersonFacade;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Optional;

import static org.springframework.http.HttpStatus.OK;

@RestController
@RequiredArgsConstructor
public class PersonController {

    private final PersonFacade personFacade;

    @GetMapping(value = "/users/persons")
    @ResponseStatus(OK)
    public List<PersonDto> getPersonsByUser(HttpServletRequest request,
                                            @RequestParam(defaultValue = "0") int page,
                                            @RequestParam(defaultValue = "10") int size) {
        Long userId = Optional.ofNullable(request.getAttribute("userId"))
                .map(Object::toString)
                .map(Long::parseLong)
                .orElse(null);
        return personFacade.getPersonsByUserId(userId, page, size);
    }

}
