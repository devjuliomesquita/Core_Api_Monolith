package com.juliomesquita.coreapi.access_control.infra.services.authetication.factories;

import com.juliomesquita.coreapi.access_control.domain.entities.Token;
import com.juliomesquita.coreapi.access_control.domain.entities.User;
import com.juliomesquita.coreapi.access_control.domain.enums.TokenType;

import java.util.UUID;

public class TokenFactory {
    public static Token createToken(User user, String token){
        return Token
                .builder()
                .id(UUID.randomUUID())
                .value(token)
                .tokenType(TokenType.BEARER)
                .expired(false)
                .revoked(false)
                .user(user)
                .build();
    }
}
