package com.romecka.fakeforge.config;

import com.romecka.fakeforge.domain.entities.ApiKey;
import com.romecka.fakeforge.exception.UnauthorizedException;
import com.romecka.fakeforge.service.ApiKeyService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

import java.io.IOException;
import java.util.Optional;

@Component
@RequiredArgsConstructor
public class ApiKeyInterceptor implements HandlerInterceptor {

    private final ApiKeyService apiKeyService;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws IOException {
        String apiKey = request.getHeader("X-API-KEY");

        if (apiKey == null || apiKey.isBlank()) {
            throw new UnauthorizedException("Missing API key");
        }

        Optional<ApiKey> key = apiKeyService.findByRawApiKey(apiKey);
        if (key.isEmpty()) {
            throw new UnauthorizedException("Invalid API key");
        }

        request.setAttribute("userId", key.get().getUser().getId());

        return true;
    }


}
