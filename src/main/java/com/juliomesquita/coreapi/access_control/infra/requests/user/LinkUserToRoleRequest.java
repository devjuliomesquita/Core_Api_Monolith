package com.juliomesquita.coreapi.access_control.infra.requests.user;

import java.util.UUID;

public record LinkUserToRoleRequest(
        UUID userId,
        UUID roleId
) {
}
