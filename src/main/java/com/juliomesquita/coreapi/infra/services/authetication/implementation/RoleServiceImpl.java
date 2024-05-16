package com.juliomesquita.coreapi.infra.services.authetication.implementation;

import com.juliomesquita.coreapi.domain.entities.Role;
import com.juliomesquita.coreapi.domain.enums.SortType;
import com.juliomesquita.coreapi.domain.interfaces.persistence.RoleRepository;
import com.juliomesquita.coreapi.domain.interfaces.services.RoleService;
import com.juliomesquita.coreapi.infra.requests.common.PageableRequest;
import com.juliomesquita.coreapi.infra.requests.roles.CreateRoleRequest;
import com.juliomesquita.coreapi.infra.requests.roles.UpdateRoleRequest;
import com.juliomesquita.coreapi.infra.responses.role.RoleResponse;
import com.juliomesquita.coreapi.infra.responses.role.RoleWithUserResponse;
import com.juliomesquita.coreapi.infra.services.authetication.mapper.RoleMapper;
import com.juliomesquita.coreapi.infra.services.authetication.mapper.UserMapper;
import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class RoleServiceImpl implements RoleService {
    private final RoleRepository roleRepository;

    @Transactional
    @Override
    public RoleResponse create(CreateRoleRequest request) {
        Role entity = RoleMapper.toEntity(request);
        Role entitySaved = this.roleRepository.save(entity);
        return RoleMapper.toDTO(entitySaved);
    }

    @Transactional
    @Override
    public RoleResponse update(UUID uuid, UpdateRoleRequest request) {
        Role entityOpt = this.roleRepository.findById(uuid)
                .orElseThrow(() -> new EntityNotFoundException("Entidade não encontrada."));
        Role entity = RoleMapper.toEntity(request, entityOpt);
        Role entitySaved = this.roleRepository.save(entity);
        return RoleMapper.toDTO(entitySaved);
    }

    @Transactional
    @Override
    public RoleResponse findById(UUID uuid) {
        Role entity = this.roleRepository.findById(uuid)
                .orElseThrow(() -> new EntityNotFoundException("Entidade não encontrada."));
        return RoleMapper.toDTO(entity);
    }

    @Transactional
    @Override
    public Page<RoleResponse> findAll(PageableRequest pageableRequest) {
        Page<Role> rolePage = this.roleRepository.findByNomeContainingIgnoreCase(
                pageableRequest.word(),
                this.createPageRequest(pageableRequest)
        );
        return rolePage.map(RoleMapper::toDTO);
    }

    @Transactional
    @Override
    public void delete(UUID uuid) {
        Role entity = this.roleRepository.findById(uuid)
                .orElseThrow(() -> new EntityNotFoundException("Entidade não encontrada."));
        if (!entity.getUsers().isEmpty()) {
            throw new RuntimeException("Role contém usuários.");
        }
        this.roleRepository.delete(entity);
    }

    @Transactional
    @Override
    public RoleWithUserResponse listUserByRole(UUID uuid) {
        Role entity = this.roleRepository.findById(uuid)
                .orElseThrow(() -> new EntityNotFoundException("Entidade não encontrada."));

        return RoleWithUserResponse
                .builder()
                .uuid(entity.getId())
                .name(entity.getName())
                .description(entity.getDescription())
                .users(
                        entity.getUsers() != null
                                ? entity.getUsers().stream().map(UserMapper::toDTOUserWithoutRole).toList()
                                : new ArrayList<>()
                )
                .build();
    }

    private PageRequest createPageRequest(PageableRequest pageableRequest) {
        Sort.Direction direction;
        if (pageableRequest.sortType() == SortType.DESC) {
            direction = Sort.Direction.DESC;
        } else {
            direction = Sort.Direction.ASC;
        }
        Sort sort = Sort.by(direction, "name");
        return PageRequest.of(pageableRequest.page(), pageableRequest.perPage(), sort);
    }

}
