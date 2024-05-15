package com.juliomesquita.coreapi.infra.services.authetication.factories;

import com.juliomesquita.coreapi.domain.entities.Token;
import com.juliomesquita.coreapi.domain.entities.User;
import com.juliomesquita.coreapi.domain.enums.TokenType;

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
