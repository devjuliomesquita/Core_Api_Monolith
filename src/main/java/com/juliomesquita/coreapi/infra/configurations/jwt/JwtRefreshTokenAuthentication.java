package com.juliomesquita.coreapi.infra.configurations.jwt;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.juliomesquita.coreapi.domain.entities.Token;
import com.juliomesquita.coreapi.domain.entities.User;
import com.juliomesquita.coreapi.domain.interfaces.persistence.TokenRepository;
import com.juliomesquita.coreapi.domain.interfaces.persistence.UserRepository;
import com.juliomesquita.coreapi.infra.responses.AuthenticationResponse;
import com.juliomesquita.coreapi.infra.services.authetication.factories.ExtraClaimsFactory;
import com.juliomesquita.coreapi.infra.services.authetication.factories.TokenFactory;
import jakarta.persistence.EntityNotFoundException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Component
@RequiredArgsConstructor
public class JwtRefreshTokenAuthentication {
    private final UserRepository userRepository;
    private final JwtService jwtService;
    private final TokenRepository tokenRepository;
    private final JwtRevokeTokens jwtRevokeTokens;

    public void refreshToken(
            HttpServletRequest request,
            HttpServletResponse response
    ) throws IOException {
        final String authHeader = request.getHeader(HttpHeaders.AUTHORIZATION);
        final String refreshToken;
        final String username;
        if (authHeader == null || !authHeader.startsWith("Bearer ")) {
            return;
        }
        refreshToken = authHeader.substring(7);
        username = this.jwtService.extractUsername(refreshToken);
        if (username != null) {
            User user = this.userRepository.findByEmail(username)
                    .orElseThrow(() -> new EntityNotFoundException("Usuário não encontrado."));
            if (this.jwtService.isTokenValid(refreshToken, user)) {
                String accessToken = this.jwtService.generateAccessToken(ExtraClaimsFactory.extraClaims(user), user);
                Token token = TokenFactory.createToken(user, accessToken);
                this.jwtRevokeTokens.revokeTokens(user);
                this.tokenRepository.save(token);
                AuthenticationResponse authResponse = new AuthenticationResponse(accessToken, refreshToken);
                new ObjectMapper().writeValue(response.getOutputStream(), authResponse);
            }
        }
    }
}
