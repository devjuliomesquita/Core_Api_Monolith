package com.juliomesquita.coreapi.infra.security.requests;

public record RegisterRequest(
        String email,
        String password,
        String confirmPassword
) {
}
