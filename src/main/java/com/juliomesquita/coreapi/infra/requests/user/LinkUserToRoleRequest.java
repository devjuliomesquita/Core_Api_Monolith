package com.juliomesquita.coreapi.infra.requests.user;

import java.util.UUID;

public record LinkUserToRoleRequest(
        UUID userId,
        UUID roleId
) {
}
