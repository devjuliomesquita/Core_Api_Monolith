package com.juliomesquita.coreapi.infra.responses.user;

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
