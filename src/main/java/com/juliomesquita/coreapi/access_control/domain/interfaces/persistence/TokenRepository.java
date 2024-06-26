package com.juliomesquita.coreapi.access_control.domain.interfaces.persistence;

import com.juliomesquita.coreapi.access_control.domain.entities.Token;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public interface TokenRepository extends JpaRepository<Token, UUID> {

    List<Token> findByUserIdAndExpiredFalseAndRevokedFalse(UUID userId);
    Optional<Token> findByValue(String value);
}
