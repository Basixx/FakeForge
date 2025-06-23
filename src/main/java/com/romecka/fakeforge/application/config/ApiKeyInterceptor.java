package com.romecka.fakeforge.application.config;

import com.romecka.fakeforge.application.service.exception.UnauthorizedException;
import com.romecka.fakeforge.domain.apikey.ApiKeyDto;
import com.romecka.fakeforge.domain.apikey.ApiKeyService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.jetbrains.annotations.NotNull;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

@Component
@RequiredArgsConstructor
public class ApiKeyInterceptor implements HandlerInterceptor {

    private final ApiKeyService apiKeyService;

    @Override
    public boolean preHandle(@NotNull HttpServletRequest request,
                             @NotNull HttpServletResponse response,
                             @NotNull Object handler) {
        String apiKey = request.getHeader("X-API-KEY");
        if (apiKey == null || apiKey.isBlank()) {
            throw new UnauthorizedException("Missing API key");
        }
        ApiKeyDto key = apiKeyService.findByRawApiKey(apiKey);

        request.setAttribute("currentUserId", key.userId());
        return true;
    }

}
