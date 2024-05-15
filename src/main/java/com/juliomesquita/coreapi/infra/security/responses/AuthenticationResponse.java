package com.juliomesquita.coreapi.infra.security.responses;

public record AuthenticationResponse(
        String accessToken,
        String refreshToken
) {
}
