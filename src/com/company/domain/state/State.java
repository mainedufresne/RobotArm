package com.company.domain.state;

import com.company.domain.state.exceptions.InsufficientBlocksException;
import com.company.domain.state.exceptions.InvalidSlotException;

import java.util.Arrays;

public final class State {
    private final int[] slots;

    public State(final int numberOfSlots) {
        if (numberOfSlots < 1) {
            throw new IllegalArgumentException("Argument 'numberOfSlots' must be greater than 0");
        }

        slots = new int[numberOfSlots];
    }

    public State(final int[] slotState) {
        slots = Arrays.copyOf(slotState, slotState.length);
    }

    public int[] getSlots() {
        return slots;
    }

    public State addBlocksToSlot(final int slot, final int numberOfBlocks) throws InvalidSlotException {
        checkSlotIsValid(slot);
        int idx = slot - 1;
        int[] slotState = copySlotState();

        slotState[idx] = slotState[idx] + numberOfBlocks;
        return new State(slotState);
    }

    public State removeBlocksFromSlot(final int slot, final int numberOfBlocks) throws InvalidSlotException, InsufficientBlocksException {
        checkSlotIsValid(slot);
        int idx = slot - 1;
        if (slots[idx] < numberOfBlocks) {
            throw new InsufficientBlocksException(numberOfBlocks, slots[idx]);
        }
        int[] slotState = copySlotState();

        slotState[idx] = slotState[idx] - numberOfBlocks;
        return new State(slotState);
    }

    private int[] copySlotState() {
        return Arrays.copyOf(slots, slots.length);
    }

    private void checkSlotIsValid(int slot) throws InvalidSlotException {
        if (slot > slots.length) {
            throw new InvalidSlotException(slot, slots.length);
        }
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        State state = (State) o;
        return Arrays.equals(slots, state.slots);
    }

    @Override
    public int hashCode() {
        return Arrays.hashCode(slots);
    }
}
