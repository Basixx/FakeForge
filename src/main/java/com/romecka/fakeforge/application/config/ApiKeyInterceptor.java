package com.romecka.fakeforge.application.config;

import com.romecka.fakeforge.application.service.exception.UnauthorizedException;
import com.romecka.fakeforge.domain.apikey.ApiKeyService;
import com.romecka.fakeforge.infrastructure.db.apikey.ApiKey;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.jetbrains.annotations.NotNull;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

import java.util.Optional;

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
        Optional<ApiKey> key = apiKeyService.findByRawApiKey(apiKey);
        if (key.isEmpty()) {
            throw new UnauthorizedException("Invalid API key");
        }
        request.setAttribute("currentUser", key.get().getUser());
        return true;
    }

}
