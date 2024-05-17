package com.juliomesquita.coreapi.access_control.infra.requests.authentication;

public record RegisterRequest(
        String email,
        String password,
        String confirmPassword
) {
}
