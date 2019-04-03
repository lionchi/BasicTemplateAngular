package com.gavrilov.core.specification;

import com.gavrilov.core.domain.User;
import com.gavrilov.core.domain.User_;
import org.springframework.data.jpa.domain.Specification;

import javax.validation.constraints.NotNull;

public final class UserSpecification {
    public static Specification<User> findById(@NotNull Long id) {
        return (root, criteriaQuery, criteriaBuilder) -> criteriaBuilder.equal(root.get(User_.ID), id);
    }
}
