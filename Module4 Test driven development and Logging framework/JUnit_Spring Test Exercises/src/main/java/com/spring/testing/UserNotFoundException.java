package com.spring.testing;

/**
 * Exercise 6: Custom Business Rule Exception for Missing Resources.
 */
public class UserNotFoundException extends RuntimeException {
    private static final long serialVersionUID = 1L;

    public UserNotFoundException(String message) {
        super(message);
    }
}