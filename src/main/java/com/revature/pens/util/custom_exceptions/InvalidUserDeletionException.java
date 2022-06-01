package com.revature.pens.util.custom_exceptions;

public class InvalidUserDeletionException extends RuntimeException{
    public InvalidUserDeletionException(String message) {
        super(message);
    }
}
