package com.juliomesquita.coreapi.infra.requests.authentication;

public record RegisterRequest(
        String email,
        String password,
        String confirmPassword
) {
}
