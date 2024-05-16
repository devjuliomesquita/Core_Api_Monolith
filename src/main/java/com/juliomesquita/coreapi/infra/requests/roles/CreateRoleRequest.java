package com.juliomesquita.coreapi.infra.requests.roles;

import com.juliomesquita.coreapi.domain.enums.PermissionsSystem;

import java.util.Set;

public record CreateRoleRequest(
       String name,
       String description,
       Set<PermissionsSystem> permissions
) {
}
