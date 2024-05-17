package com.juliomesquita.coreapi.access_control.infra.requests.roles;

import com.juliomesquita.coreapi.access_control.domain.enums.PermissionsSystem;

import java.util.Set;

public record UpdateRoleRequest(
        String description,
        Set<PermissionsSystem> permissions
) {
}
