package com.gavrilov.core.dto;

import com.gavrilov.core.domain.User;
import com.gavrilov.core.mappers.MapperFactory;
import com.gavrilov.core.mappers.UserMapper;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

public class UserDTO {

    private Long id;

    @NotEmpty(message = "Поле login недолжно быть пустым")
    private String login;

    @NotEmpty(message = "Поле password недолжно быть пустым")
    @Length(min = 7, message = "Длина пароля должна быть от 7 символов")
    private String password;

    @NotEmpty(message = "Поле fio недолжно быть пустым")
    private String fio;

    @NotEmpty(message = "Поле email недолжно быть пустым")
    private String email;

    private Integer enabled;

    public UserDTO() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getFio() {
        return fio;
    }

    public void setFio(String fio) {
        this.fio = fio;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Integer getEnabled() {
        return enabled;
    }

    public void setEnabled(Integer enabled) {
        this.enabled = enabled;
    }

    public static UserDTO userAsUserDTO(User user) {
        UserMapper mapper = MapperFactory.createMapper(UserMapper.class);
        return mapper.asUserDTO(user);
    }

    public static User UserDTOAsUser(UserDTO userDTO) {
        UserMapper mapper = MapperFactory.createMapper(UserMapper.class);
        return mapper.asUser(userDTO);
    }
}
