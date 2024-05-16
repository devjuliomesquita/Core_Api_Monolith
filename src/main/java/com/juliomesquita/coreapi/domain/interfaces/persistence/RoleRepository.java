package com.juliomesquita.coreapi.domain.interfaces.persistence;

import com.juliomesquita.coreapi.domain.entities.Role;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface RoleRepository extends JpaRepository<Role, UUID> {
    Optional<Role> findByName(String name);
    Page<Role> findByNameContainingIgnoreCase(String name, Pageable pageable);
}
