package com.juliomesquita.coreapi.access_control.domain.interfaces.services;

import com.juliomesquita.coreapi.access_control.infra.requests.common.PageableRequest;
import com.juliomesquita.coreapi.access_control.infra.requests.roles.CreateRoleRequest;
import com.juliomesquita.coreapi.access_control.infra.requests.roles.UpdateRoleRequest;
import com.juliomesquita.coreapi.access_control.infra.responses.role.RoleResponse;
import com.juliomesquita.coreapi.access_control.infra.responses.role.RoleWithUserResponse;
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
