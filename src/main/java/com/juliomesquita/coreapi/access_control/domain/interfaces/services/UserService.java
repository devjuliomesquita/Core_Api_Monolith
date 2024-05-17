package com.juliomesquita.coreapi.access_control.domain.interfaces.services;

import com.juliomesquita.coreapi.access_control.infra.requests.user.LinkUserToRoleRequest;
import com.juliomesquita.coreapi.access_control.infra.responses.user.UserResponse;

public interface UserService {
    UserResponse linkUserToRole(LinkUserToRoleRequest request);
}
