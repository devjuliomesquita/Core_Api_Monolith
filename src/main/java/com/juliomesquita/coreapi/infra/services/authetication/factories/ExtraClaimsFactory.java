package com.juliomesquita.coreapi.infra.services.authetication.factories;

import com.juliomesquita.coreapi.domain.entities.User;

import java.util.HashMap;
import java.util.Map;

public class ExtraClaimsFactory {
    private ExtraClaimsFactory() {
    }

    public static Map<String, Object> extraClaims(User user) {
        Map<String, Object> extraClaims = new HashMap<>();
        extraClaims.put("Id", user.getId());
        extraClaims.put("Role", user.getRole().getName());
        extraClaims.put("Permissions", user.getRole().getAuthoraties());
        return extraClaims;
    }
}
