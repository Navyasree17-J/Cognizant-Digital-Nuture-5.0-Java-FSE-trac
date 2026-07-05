package com.spring.testing;

import java.util.Optional;

/**
 * Exercise 2: Mockable Data Access Repository Interface Layer.
 */
public interface UserRepository {
    Optional<User> findById(Long id);
}