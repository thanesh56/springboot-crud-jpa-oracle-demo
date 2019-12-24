package com.gl.springbootcrudjpaoracledemo.exception;

public class AlreadyExistsException extends RuntimeException {
    private static final long serialVersionUID = 1L;
    final String errorCode;

    public AlreadyExistsException(String exception, String errorCode) {
        super(exception);
        this.errorCode = errorCode;
    }
}
