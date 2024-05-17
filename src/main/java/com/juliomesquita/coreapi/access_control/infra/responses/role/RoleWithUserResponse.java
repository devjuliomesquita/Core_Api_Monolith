package com.juliomesquita.coreapi.access_control.infra.responses.role;

import com.juliomesquita.coreapi.access_control.infra.responses.user.UserWithoutRole;
import lombok.Builder;

import java.util.List;
import java.util.UUID;

@Builder
public record RoleWithUserResponse(
        UUID uuid,
        String name,
        String description,
        List<UserWithoutRole> users
) {
}
