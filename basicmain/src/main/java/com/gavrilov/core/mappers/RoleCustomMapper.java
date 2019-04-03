package com.gavrilov.core.mappers;

import com.gavrilov.core.domain.Role;
import com.gavrilov.core.domain.User;

public final class RoleCustomMapper {
    public String roleAsString (Role source) {
        return source.toString();
    }
}
