package com.spring.testing;

import org.springframework.stereotype.Service;

/**
 * Exercise 2 & 5: Service layer processing user lookups and persistent save updates.
 */
@Service
public class UserService {

    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public User getUserById(Long id) {
        return userRepository.findById(id).orElse(null); //
    }

    // NEW METHOD FOR EXERCISE 5: Handle persistence logic pipeline
    public User saveUser(User user) {
        // In a production application, this would call userRepository.save(user)
        System.out.println("💾 [Service Layer] Processing and saving user payload: " + user.getName());
        return user;
    }
}