package com.juliomesquita.coreapi.infra.responses.user;

import lombok.Builder;

import java.util.UUID;

@Builder
public record UserWithoutRole(
        UUID id,
        String email
) {
}
