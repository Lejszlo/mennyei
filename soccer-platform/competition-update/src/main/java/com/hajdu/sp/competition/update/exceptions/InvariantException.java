package com.hajdu.sp.competition.update.exceptions;

public class InvariantException extends RuntimeException {
    private ExceptionValue<?> exceptionValue;

    public InvariantException(String message, ExceptionValue<?> exceptionValue) {
        super(message);
        this.exceptionValue = exceptionValue;
    }
}
