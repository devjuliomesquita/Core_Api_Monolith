package com.juliomesquita.coreapi.domain.interfaces.services;

import com.juliomesquita.coreapi.infra.requests.common.PageableRequest;
import com.juliomesquita.coreapi.infra.requests.roles.CreateRoleRequest;
import com.juliomesquita.coreapi.infra.requests.roles.UpdateRoleRequest;
import com.juliomesquita.coreapi.infra.responses.role.RoleResponse;
import com.juliomesquita.coreapi.infra.responses.role.RoleWithUserResponse;
import org.springframework.data.domain.Page;

import java.util.UUID;

public interface RoleService {
    RoleResponse create(CreateRoleRequest request);
    RoleResponse update(UUID uuid, UpdateRoleRequest request);
    RoleResponse findById(UUID uuid);
    Page<RoleResponse> findAll(PageableRequest pageableRequest);
    void delete(UUID uuid);
    RoleWithUserResponse listUserByRole(UUID uuid);
}
