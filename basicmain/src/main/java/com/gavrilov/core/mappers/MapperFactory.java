package com.gavrilov.core.mappers;

import fr.xebia.extras.selma.Selma;

@SuppressWarnings("uncheecked")
public class MapperFactory {
    public static <T> T createMapper(Class<T> typeMapperClass) {
        if (typeMapperClass == UserMapper.class) {
            return (T) Selma.builder(UserMapper.class).build();
        } else {
            return (T) Selma.builder(RoleMapper.class).build();
        }
    }
}
