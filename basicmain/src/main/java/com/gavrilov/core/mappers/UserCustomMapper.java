package com.gavrilov.core.mappers;

import com.gavrilov.core.domain.User;

public final class UserCustomMapper {
    public String userAsString (User source) {
        return source.toString();
    }
}
