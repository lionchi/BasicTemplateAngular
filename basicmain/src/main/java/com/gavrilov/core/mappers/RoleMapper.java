package com.gavrilov.core.mappers;

import com.gavrilov.core.domain.Role;
import com.gavrilov.core.dto.RoleDTO;
import fr.xebia.extras.selma.IgnoreMissing;
import fr.xebia.extras.selma.Mapper;

@Mapper(withCustom = RoleCustomMapper.class, withIgnoreMissing = IgnoreMissing.ALL, withIgnoreFields = "users")
public interface RoleMapper {
    RoleDTO asUserDTO (Role role);

    Role asUser (RoleDTO source);
}
