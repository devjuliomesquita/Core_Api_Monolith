package com.juliomesquita.coreapi.access_control.infra.configurations.jwt;

import com.juliomesquita.coreapi.access_control.domain.entities.Token;
import com.juliomesquita.coreapi.access_control.domain.interfaces.persistence.TokenRepository;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.logout.LogoutHandler;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class JwtLogoutService implements LogoutHandler {
    private final TokenRepository tokenRepository;

    @Override
    public void logout(
            HttpServletRequest request,
            HttpServletResponse response,
            Authentication authentication
    ) {
        final String authHeader = request.getHeader("Authorization");
        final String jwt;
        if (authHeader == null || !authHeader.startsWith("Bearer ")) {
            return;
        }
        jwt = authHeader.substring(7);
        Token tokenSaved = this.tokenRepository.findByValue(jwt).orElse(null);
        if (tokenSaved != null) {
            tokenSaved.setExpired(true);
            tokenSaved.setRevoked(true);
            this.tokenRepository.save(tokenSaved);
        }
    }
}
