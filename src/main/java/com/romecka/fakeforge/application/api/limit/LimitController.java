package com.romecka.fakeforge.application.api.limit;

import com.romecka.fakeforge.domain.limit.LimitService;
import com.romecka.fakeforge.domain.user.UserAuthenticationDetails;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import static org.springframework.http.HttpStatus.OK;

@RestController
@RequiredArgsConstructor
public class
LimitController {

    private final LimitService limitService;

    @GetMapping(value = "/users/limit")
    @ResponseStatus(OK)
    @PreAuthorize("hasRole('USER')")
    public LimitResponse getUserLimit(@AuthenticationPrincipal UserAuthenticationDetails userDetails) {
        return LimitResponse.of(limitService.getUserLimit(userDetails.getUserId()));
    }

}
