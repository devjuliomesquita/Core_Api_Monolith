package com.juliomesquita.coreapi.domain.interfaces.services;

import com.juliomesquita.coreapi.infra.requests.user.LinkUserToRoleRequest;
import com.juliomesquita.coreapi.infra.responses.user.UserResponse;

public interface UserService {
    UserResponse linkUserToRole(LinkUserToRoleRequest request);
}
