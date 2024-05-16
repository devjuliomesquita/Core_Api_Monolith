package com.juliomesquita.coreapi.infra.responses;

import com.juliomesquita.coreapi.infra.responses.role.RoleResponse;
import lombok.Builder;

import java.util.UUID;

@Builder
public record UserResponse(
        UUID id,
        String email,
        RoleResponse role
) {
}
