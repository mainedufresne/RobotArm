package com.company.domain.commands.exceptions;

/**
 * Used to indicate that an argument passed to a command is invalid.
 * The message of the Exception should indicate the error and how to correct.
 */
public final class InvalidCommandArgumentException extends Exception {

    /**
     * @param message Should be a human readable error message indicating how the user can correct the error
     */
    public InvalidCommandArgumentException(String message) {
        super(message);
    }
}
