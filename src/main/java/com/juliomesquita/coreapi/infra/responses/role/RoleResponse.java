package com.juliomesquita.coreapi.infra.responses.role;

import com.juliomesquita.coreapi.domain.enums.PermissionsSystem;
import lombok.Builder;

import java.util.Set;
import java.util.UUID;

@Builder
public record RoleResponse(
        UUID id,
        String name,
        String description,
        Set<PermissionsSystem> permissions
) {
}
