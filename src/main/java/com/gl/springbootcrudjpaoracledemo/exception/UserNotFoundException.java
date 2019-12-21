package com.gl.springbootcrudjpaoracledemo.exception;

public class UserNotFoundException extends IllegalArgumentException{
    private Long userId;
    public UserNotFoundException(Long userId) {
        this.userId = userId;
    }
}
