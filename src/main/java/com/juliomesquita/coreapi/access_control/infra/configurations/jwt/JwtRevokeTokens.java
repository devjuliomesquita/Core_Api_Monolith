package com.juliomesquita.coreapi.access_control.infra.configurations.jwt;

import com.juliomesquita.coreapi.access_control.domain.entities.Token;
import com.juliomesquita.coreapi.access_control.domain.entities.User;
import com.juliomesquita.coreapi.access_control.domain.interfaces.persistence.TokenRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@RequiredArgsConstructor
public class JwtRevokeTokens {
    private final TokenRepository tokenRepository;

    public void revokeTokens(User user) {
        List<Token> tokenList =
                this.tokenRepository.findByUserIdAndExpiredFalseAndRevokedFalse(user.getId());
        if (tokenList.isEmpty()) {
            return;
        }
        tokenList.forEach(t -> {
            t.setRevoked(true);
            t.setExpired(true);
        });
        this.tokenRepository.saveAll(tokenList);
    }
}
