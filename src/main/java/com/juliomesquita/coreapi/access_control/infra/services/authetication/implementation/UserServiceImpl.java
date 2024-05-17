package com.juliomesquita.coreapi.access_control.infra.services.authetication.implementation;

import com.juliomesquita.coreapi.access_control.domain.entities.Role;
import com.juliomesquita.coreapi.access_control.domain.entities.User;
import com.juliomesquita.coreapi.access_control.infra.responses.user.UserResponse;
import com.juliomesquita.coreapi.access_control.infra.services.authetication.mapper.UserMapper;
import com.juliomesquita.coreapi.access_control.domain.interfaces.persistence.RoleRepository;
import com.juliomesquita.coreapi.access_control.domain.interfaces.persistence.UserRepository;
import com.juliomesquita.coreapi.access_control.domain.interfaces.services.UserService;
import com.juliomesquita.coreapi.access_control.infra.requests.user.LinkUserToRoleRequest;
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
