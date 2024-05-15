package com.juliomesquita.coreapi.infra.services.authetication.factories;

import com.juliomesquita.coreapi.domain.entities.Role;
import com.juliomesquita.coreapi.domain.enums.PermissionsSystem;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Component
public class RoleFactory {
    public List<Role> createRoles() {
        List<String> roleNames = new ArrayList<>(
                List.of("ADMIN", "USER")
        );
        return roleNames
                .stream()
                .map(this::createRole)
                .collect(Collectors.toList());
    }

    private Role createRole(String name) {
        return Role
                .builder()
                .id(UUID.randomUUID())
                .name(name)
                .description("Perfil do " + name)
                .permissions(PermissionsSystem.getPermissions(name))
                .build();
    }

}
