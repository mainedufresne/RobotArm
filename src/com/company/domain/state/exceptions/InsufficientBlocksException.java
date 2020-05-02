package com.company.domain.state.exceptions;

public class InsufficientBlocksException extends Exception {
    private static final String MESSAGE_FORMAT = "Not enough blocks in slot! Tried to remove %d blocks. Slot contains %d blocks.";

    public InsufficientBlocksException(final int numberRequested, final int numberAvailable) {
        super(String.format(MESSAGE_FORMAT, numberRequested, numberAvailable));
    }
}
