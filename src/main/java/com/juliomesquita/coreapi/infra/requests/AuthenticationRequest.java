package com.juliomesquita.coreapi.infra.requests;

public record AuthenticationRequest(
        String email,
        String password
) {
}
