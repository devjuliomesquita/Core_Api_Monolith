package com.juliomesquita.coreapi.access_control.infra.requests.roles;

import com.juliomesquita.coreapi.access_control.domain.enums.PermissionsSystem;

import java.util.Set;

public record CreateRoleRequest(
       String name,
       String description,
       Set<PermissionsSystem> permissions
) {
}
