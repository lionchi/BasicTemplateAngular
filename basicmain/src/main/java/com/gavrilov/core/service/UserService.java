package com.gavrilov.core.service;

import com.gavrilov.core.domain.User;
import com.gavrilov.core.dto.UserDTO;
import com.gavrilov.core.mappers.MapperFactory;
import com.gavrilov.core.mappers.UserMapper;
import com.gavrilov.core.repository.RoleRepository;
import com.gavrilov.core.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.LinkedList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Service
@Transactional
public class UserService {
    private final RoleRepository roleRepository;
    private final UserRepository userRepository;

    @Autowired
    public UserService(RoleRepository roleRepository, UserRepository userRepository) {
        this.roleRepository = roleRepository;
        this.userRepository = userRepository;
    }

    @Transactional(readOnly = true)
    public UserDTO getUserById(Long id) {
        UserMapper userMapper = MapperFactory.createMapper(UserMapper.class);
        Optional<User> userById = userRepository.findById(id);
        return userMapper.asUserDTO(userById.orElse(null));
    }

    @Transactional(readOnly = true)
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public LinkedList<UserDTO> getUserList() {
        return userRepository.findAll()
                .stream()
                .map(UserDTO::userAsUserDTO)
                .collect(Collectors.toCollection(LinkedList::new));

    }

    @Transactional(readOnly = true)
    public User findOne(String login) {
        return userRepository.findByLogin(login).orElse(null);
    }
}
