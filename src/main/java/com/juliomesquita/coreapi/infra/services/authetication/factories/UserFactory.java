package com.juliomesquita.coreapi.infra.services.authetication.factories;

import com.juliomesquita.coreapi.domain.entities.Role;
import com.juliomesquita.coreapi.domain.entities.User;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.UUID;
@Component
@RequiredArgsConstructor
public class UserFactory {
    private final PasswordEncoder passwordEncoder;

    public User createUser(String email, String password, Role role){
        return User
                .builder()
                .id(UUID.randomUUID())
                .email(email)
                .password(this.passwordEncoder.encode(password))
                .role(role)
                .build();
    }
}
