package com.juliomesquita.coreapi.infra.requests.authentication;

public record AuthenticationRequest(
        String email,
        String password
) {
}
