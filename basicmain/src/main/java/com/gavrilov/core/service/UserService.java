package com.gavrilov.core.service;

import com.gavrilov.core.domain.Role;
import com.gavrilov.core.domain.User;
import com.gavrilov.core.dto.UserDTO;
import com.gavrilov.core.exception.ModelValidationException;
import com.gavrilov.core.mappers.MapperFactory;
import com.gavrilov.core.mappers.UserMapper;
import com.gavrilov.core.repository.RoleRepository;
import com.gavrilov.core.repository.UserRepository;
import org.apache.commons.text.CharacterPredicates;
import org.apache.commons.text.RandomStringGenerator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import java.util.Collections;
import java.util.LinkedList;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@Transactional
public class UserService {
    private final RoleRepository roleRepository;
    private final UserRepository userRepository;
    private final EntityManager entityManager;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;

    @Autowired
    public UserService(RoleRepository roleRepository, UserRepository userRepository, EntityManager entityManager,
                       BCryptPasswordEncoder bCryptPasswordEncoder) {
        this.roleRepository = roleRepository;
        this.userRepository = userRepository;
        this.entityManager = entityManager;
        this.bCryptPasswordEncoder = bCryptPasswordEncoder;
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

    public void saveUser(UserDTO userDTO) {
        User user = UserDTO.UserDTOAsUser(userDTO);
        Role role = roleRepository.findByRolename("ROLE_USER");
        user.setRoles(Collections.singletonList(role));
        user.setPassword(bCryptPasswordEncoder.encode(userDTO.getPassword()));
        user.setEnabled(1);
        userRepository.save(user);
    }

    @Transactional(readOnly = true)
    public void validationNewUser(UserDTO userDTO) {
        Optional<User> byLogin = userRepository.findByLogin(userDTO.getLogin());
        Optional<User> byEmail = userRepository.findByEmail(userDTO.getEmail());
        if (byLogin.isPresent() || byEmail.isPresent()) {
            throw new ModelValidationException("error.user.validation", "Пользователь с таким логином или email уже существует");
        }
    }

    public String validationEmail(String email) {
        Optional<User> byEmail = userRepository.findByEmail(email);
        if (!byEmail.isPresent()) {
            throw new ModelValidationException("error.user.validation.email", "Такого email в системе не найдено");
        }
        User user = byEmail.get();
        String newPassword = generationNewPassword();
        String encodeNewPassword = bCryptPasswordEncoder.encode(generationNewPassword());
        userRepository.updatePassword(encodeNewPassword, user.getId());
        return newPassword;
    }

    private String generationNewPassword() {
        RandomStringGenerator randomStringGenerator = new RandomStringGenerator.Builder()
                .withinRange('0', 'z')
                .filteredBy(CharacterPredicates.LETTERS, CharacterPredicates.DIGITS)
                .build();
        return randomStringGenerator.generate(10);
    }
}
