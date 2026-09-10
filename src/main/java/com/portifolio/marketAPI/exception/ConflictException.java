package com.portifolio.marketAPI.exception;

public class ConflictException extends RuntimeException {
    public ConflictException(String message) {
        super(message);
    }
    //409
}
