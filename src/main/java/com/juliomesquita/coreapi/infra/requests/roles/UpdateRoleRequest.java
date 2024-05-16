package com.juliomesquita.coreapi.infra.requests.roles;

import com.juliomesquita.coreapi.domain.enums.PermissionsSystem;

import java.util.Set;

public record UpdateRoleRequest(
        String description,
        Set<PermissionsSystem> permissions
) {
}
