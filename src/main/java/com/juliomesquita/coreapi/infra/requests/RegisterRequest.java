package com.juliomesquita.coreapi.infra.requests;

public record RegisterRequest(
        String email,
        String password,
        String confirmPassword
) {
}
