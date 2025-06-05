package com.romecka.fakeforge.controller;

import com.romecka.fakeforge.domain.dto.LimitDto;
import com.romecka.fakeforge.facade.LimitFacade;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;

import static org.springframework.http.HttpStatus.OK;

@RestController
@RequiredArgsConstructor
public class LimitController {

    private final LimitFacade limitFacade;

    @GetMapping(value = "/users/limit")
    @ResponseStatus(OK)
    public LimitDto getUserLimit(HttpServletRequest request) {
        Long userId = Optional.ofNullable(request.getAttribute("userId"))
                .map(Object::toString)
                .map(Long::parseLong)
                .orElse(null);
        return limitFacade.getUserLimit(userId);
    }

}
