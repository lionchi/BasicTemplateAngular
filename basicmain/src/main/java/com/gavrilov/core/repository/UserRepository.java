package com.gavrilov.core.repository;

import com.gavrilov.core.domain.User;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
@CacheConfig(cacheNames = "users")
public interface UserRepository extends JpaRepository<User, Long>, JpaSpecificationExecutor<User> {
    @Override
    @Cacheable
    Optional<User> findById(Long aLong);

    @CachePut
    Optional<User> findByLogin (String login);

    @Cacheable
    Optional<User> findByEmail (String email);

    @Override
    List<User> findAll();
}
