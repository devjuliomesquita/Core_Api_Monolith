package com.juliomesquita.coreapi.access_control.infra.requests.authentication;

public record AuthenticationRequest(
        String email,
        String password
) {
}
