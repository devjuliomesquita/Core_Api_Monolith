package com.juliomesquita.coreapi.access_control.infra.responses;

public record AuthenticationResponse(
        String accessToken,
        String refreshToken
) {
}
