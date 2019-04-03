package com.gavrilov.core.dto;

import javax.validation.constraints.NotNull;

public class RoleDTO {
    @NotNull
    private Long id;

    public RoleDTO() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
}
