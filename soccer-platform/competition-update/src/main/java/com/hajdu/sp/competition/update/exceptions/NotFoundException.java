package com.hajdu.sp.competition.update.exceptions;

public class NotFoundException extends RuntimeException {

    public NotFoundException(String id) {
        super(String.format("The expected resource not found: %s", id));
    }

}
