package com.gavrilov.core.mappers;

import com.gavrilov.core.domain.User;
import com.gavrilov.core.dto.UserDTO;
import fr.xebia.extras.selma.Field;
import fr.xebia.extras.selma.IgnoreMissing;
import fr.xebia.extras.selma.Mapper;

@Mapper(withCustom = UserCustomMapper.class,/* withCustomFields = {@Field({"role.id", "roleId"})},*/
        withIgnoreMissing = IgnoreMissing.ALL)
public interface UserMapper {
    UserDTO asUserDTO (User user);

    User asUser (UserDTO source);
}
