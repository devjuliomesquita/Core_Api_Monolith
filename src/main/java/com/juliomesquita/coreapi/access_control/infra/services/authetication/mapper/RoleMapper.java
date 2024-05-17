package com.juliomesquita.coreapi.access_control.infra.services.authetication.mapper;

import com.juliomesquita.coreapi.access_control.domain.entities.Role;
import com.juliomesquita.coreapi.access_control.infra.responses.role.RoleResponse;
import com.juliomesquita.coreapi.access_control.infra.requests.roles.CreateRoleRequest;
import com.juliomesquita.coreapi.access_control.infra.requests.roles.UpdateRoleRequest;

import java.util.UUID;

public class RoleMapper {

    public static Role toEntity(CreateRoleRequest request){
        return Role
                .builder()
                .id(UUID.randomUUID())
                .name(request.name())
                .description(request.description())
                .permissions(request.permissions())
                .build();
    }

    public static Role toEntity(UpdateRoleRequest request, Role role){
        return Role
                .builder()
                .id(role.getId())
                .name(role.getName())
                .description(request.description())
                .permissions(request.permissions())
                .users(role.getUsers())
                .build();
    }

    public static RoleResponse toDTO(Role role){
        return RoleResponse
                .builder()
                .id(role.getId())
                .name(role.getName())
                .description(role.getDescription())
                .permissions(role.getPermissions())
                .build();
    }
}
