package com.juliomesquita.coreapi.infra.security.requests;

public record AuthenticationRequest(
        String email,
        String password
) {
}
