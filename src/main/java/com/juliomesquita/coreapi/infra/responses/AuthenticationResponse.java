package com.juliomesquita.coreapi.infra.responses;

public record AuthenticationResponse(
        String accessToken,
        String refreshToken
) {
}
