package com.romecka.fakeforge.application.api.limit;

import com.romecka.fakeforge.application.api.generic.MessageResponse;
import com.romecka.fakeforge.domain.limit.LimitService;
import com.romecka.fakeforge.domain.user.UserAuthenticationDetails;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import static org.springframework.http.HttpStatus.OK;

@RestController
@RequestMapping("/users")
@RequiredArgsConstructor
public class
LimitController {

    private final LimitService limitService;

    @GetMapping(value = "/limit")
    @ResponseStatus(OK)
    @PreAuthorize("hasRole('USER')")
    public LimitResponse getUserLimit(@AuthenticationPrincipal UserAuthenticationDetails userDetails) {
        return LimitResponse.of(limitService.getUserLimit(userDetails.getUserId()));
    }

    @PutMapping(value = "{userId}/limit")
    @ResponseStatus(OK)
    @PreAuthorize("hasRole('ADMIN')")
    public MessageResponse editUserLimit(@PathVariable long userId, @RequestParam int dailyLimit) {
        limitService.updateLimit(userId, dailyLimit);
        return new MessageResponse("Daily limit %s for user with ID %s will be refreshed after midnight".formatted(dailyLimit, userId));
    }

}
