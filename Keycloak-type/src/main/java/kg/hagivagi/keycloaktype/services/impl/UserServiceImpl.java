package kg.hagivagi.keycloaktype.services.impl;


import kg.hagivagi.keycloaktype.entities.User;
import kg.hagivagi.keycloaktype.mappers.UserMapper;
import kg.hagivagi.keycloaktype.models.FilterRequest;
import kg.hagivagi.keycloaktype.models.UserModel;
import kg.hagivagi.keycloaktype.repositories.UserRepository;
import kg.hagivagi.keycloaktype.services.UserService;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.ArrayList;
import java.util.List;

import static kg.hagivagi.keycloaktype.utils.SpecificationHelper.concatFieldsAndLower;
import static kg.hagivagi.keycloaktype.utils.SpecificationHelper.getContainsLikePattern;
import static kg.hagivagi.keycloaktype.utils.StringUtils.isEmpty;


@Slf4j
@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;
    private final UserMapper userMapper;
    private final PasswordEncoder passwordEncoder;

    @Override
    public Page<UserModel> getAll(FilterRequest filterRequest, Pageable pageable) {
        return userRepository
                .findAll(new UserSpecification(filterRequest), pageable)
                .map(userMapper::toModel);
    }

    @Override
    public UserModel save(UserModel userModel) throws Exception {
        User entity = userMapper.toEntity(userModel);
        entity.setPassword(passwordEncoder.encode(userModel.getPassword()));

        return userMapper.toModel(userRepository.save(entity));
    }

    @Override
    public UserModel update(UserModel userModel) throws Exception {
        if (userModel.getId() == null)
            throw new IllegalArgumentException("ID is required for update operation");

        User updatedUser = userRepository.save(userMapper.toEntity(userModel));
        return userMapper.toModel(updatedUser);
    }

    @Override
    public boolean deleteById(Long id) throws Exception {
        userRepository.delete(
                userRepository.findById(id)
                        .orElseThrow(() -> new Exception(String.format("No user found with id '%s'.", id)))
        );
        return true;
    }

    @Override
    public User findByLogin(String login) throws Exception {
        return userRepository.findByLogin(login)
                .orElseThrow(() -> new Exception(String.format("No user found with login '%s'.", login)));
    }

    @AllArgsConstructor
    static class UserSpecification implements Specification<User> {
        private final FilterRequest filterRequest;
        @Override
        public Predicate toPredicate(Root<User> root, CriteriaQuery<?> query, CriteriaBuilder criteriaBuilder) {
            final List<Predicate> predicates = new ArrayList<>();

            if (!isEmpty(filterRequest.getSearchSubject())) {
                predicates.add(
                        criteriaBuilder.like(
                                concatFieldsAndLower(criteriaBuilder, root, null, "name", "lastname", "patronymic", "login"),
                                getContainsLikePattern(filterRequest.getSearchSubject())
                        )
                );
            }

            return criteriaBuilder.and(predicates.toArray(new Predicate[predicates.size()]));
        }
    }
}
