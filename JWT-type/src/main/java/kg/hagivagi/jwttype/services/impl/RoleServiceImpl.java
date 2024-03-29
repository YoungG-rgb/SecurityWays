package kg.hagivagi.jwttype.services.impl;


import kg.hagivagi.jwttype.entities.Role;
import kg.hagivagi.jwttype.mappers.RoleMapper;
import kg.hagivagi.jwttype.models.RoleModel;
import kg.hagivagi.jwttype.repositories.RoleRepository;
import kg.hagivagi.jwttype.services.RoleService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;

@Slf4j
@Service
@RequiredArgsConstructor
public class RoleServiceImpl implements RoleService {
    private final RoleRepository roleRepository;
    private final RoleMapper roleMapper;

    @Override
    public List<RoleModel> getAll() {
        return roleRepository.findAll().stream().map(roleMapper::toModel).toList();
    }

    @Override
    public RoleModel save(RoleModel roleModel) {
        Role savedRole = roleRepository.save(roleMapper.toEntity(roleModel));
        return roleMapper.toModel(savedRole);
    }

    @Override
    public RoleModel update(RoleModel roleModel) {
        if (roleModel.getId() == null)
            throw new IllegalArgumentException("ID is required for update operation");

        return this.save(roleModel);
    }

    @Override
    public boolean deleteById(Long id) throws NoSuchElementException {
        roleRepository.delete(
                roleRepository.findById(id)
                        .orElseThrow(() -> new NoSuchElementException(String.format("No role found with id '%s'.", id)))
        );
        return true;
    }
}
