package com.juliomesquita.coreapi.infra.services.authetication.mapper;

import com.juliomesquita.coreapi.domain.entities.User;
import com.juliomesquita.coreapi.infra.responses.UserResponse;
import com.juliomesquita.coreapi.infra.responses.user.UserWithoutRole;

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
