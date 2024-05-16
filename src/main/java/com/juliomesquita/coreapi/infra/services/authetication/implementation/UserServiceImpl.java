package com.juliomesquita.coreapi.infra.services.authetication.implementation;

import com.juliomesquita.coreapi.domain.entities.Role;
import com.juliomesquita.coreapi.domain.entities.User;
import com.juliomesquita.coreapi.domain.interfaces.persistence.RoleRepository;
import com.juliomesquita.coreapi.domain.interfaces.persistence.UserRepository;
import com.juliomesquita.coreapi.domain.interfaces.services.UserService;
import com.juliomesquita.coreapi.infra.requests.user.LinkUserToRoleRequest;
import com.juliomesquita.coreapi.infra.responses.user.UserResponse;
import com.juliomesquita.coreapi.infra.services.authetication.mapper.UserMapper;
import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;
    private final RoleRepository roleRepository;

    @Transactional
    @Override
    public UserResponse linkUserToRole(LinkUserToRoleRequest request) {
        User userOpt = this.userRepository.findById(request.userId())
                .orElseThrow(() -> new EntityNotFoundException("Usuário não encontrado."));
        Role roleOpt = this.roleRepository.findById(request.roleId())
                .orElseThrow(() -> new EntityNotFoundException("Role não encontrada."));
        userOpt.setRole(roleOpt);
        User userSaved = this.userRepository.save(userOpt);
        return UserMapper.toDTO(userSaved);
    }
}
