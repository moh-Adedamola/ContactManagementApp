package com.semicolon.africa.exceptions;

public class UsernameOrPasswordException extends RuntimeException {
    public UsernameOrPasswordException(String message) {
        super(message);
    }
}
