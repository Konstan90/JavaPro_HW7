package ru.kononov.userproduct.interceptor;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.web.servlet.HandlerInterceptor;

public class TokenInterceptor implements HandlerInterceptor {

    private final String accessToken;

    public TokenInterceptor(String accessToken) {
        this.accessToken = accessToken;
    }

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        String token = request.getHeader("x-access-token");

        if (token == null || !isValidToken(token)) {
            response.sendError(HttpServletResponse.SC_UNAUTHORIZED, "Invalid or missing x-access-token");
            return false;  // Останавливает дальнейшую обработку запроса
        }

        return true;  // Продолжаем обработку запроса
    }

    private boolean isValidToken(String token) {
        return token.equals(accessToken);
    }
}
