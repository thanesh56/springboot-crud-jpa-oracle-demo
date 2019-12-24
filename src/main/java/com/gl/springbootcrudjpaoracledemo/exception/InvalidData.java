package com.gl.springbootcrudjpaoracledemo.exception;

public class InvalidData extends RuntimeException {
    private static final long serialVersionUID = 1L;

    public InvalidData(String exception) {
        super(exception);
    }
}