package com.juliomesquita.coreapi.access_control.infra.services.authetication.mapper;

import com.juliomesquita.coreapi.access_control.domain.entities.User;
import com.juliomesquita.coreapi.access_control.infra.responses.user.UserResponse;
import com.juliomesquita.coreapi.access_control.infra.responses.user.UserWithoutRole;

public class UserMapper {

    public static UserResponse toDTO(User user){
        return UserResponse
                .builder()
                .id(user.getId())
                .email(user.getEmail())
                .role(RoleMapper.toDTO(user.getRole()))
                .build();

    }

    public static UserWithoutRole toDTOUserWithoutRole(User user){
        return UserWithoutRole
                .builder()
                .id(user.getId())
                .email(user.getEmail())
                .build();

    }

}
