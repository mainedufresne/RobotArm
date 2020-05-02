package com.company.domain.commands.exceptions;

public final class InvalidCommandException extends RuntimeException {

    public InvalidCommandException(String message) {
        super(message);
    }

}
