package com.gavrilov.core.dto;

import com.gavrilov.core.domain.User;
import com.gavrilov.core.mappers.MapperFactory;
import com.gavrilov.core.mappers.UserMapper;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

public class UserDTO {
    @NotNull
    private Long id;

    @NotEmpty(message = "Это поле недолжно быть пустым")
    private String login;

    @NotEmpty(message = "Это поле недолжно быть пустым")
    @Length(min = 6, message = "Длина пароля должна быть от 6 символов")
    private String password;

    @NotEmpty(message = "Это поле недолжно быть пустым")
    private String fio;

    @NotNull
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
}
