package com.revature.pens.util.custom_exceptions;

public class DatabaseAccessException extends RuntimeException{
    public DatabaseAccessException(String message) {
        super(message);
    }
}
