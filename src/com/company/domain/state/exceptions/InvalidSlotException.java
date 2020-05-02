package com.company.domain.state.exceptions;

public class InvalidSlotException extends Exception {
    private static final String MESSAGE_FORMAT = "Slot %d is invalid, please choose a slot between 1 and %d";
    public InvalidSlotException(int slotChosen, int maxSlot) {
        super(String.format(MESSAGE_FORMAT, slotChosen, maxSlot));
    }
}
