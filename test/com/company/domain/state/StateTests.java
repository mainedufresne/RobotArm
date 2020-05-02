package com.company.domain.state;

import com.company.domain.state.exceptions.InsufficientBlocksException;
import com.company.domain.state.exceptions.InvalidSlotException;
import org.junit.Assert;
import org.junit.Test;

public class StateTests {

    @Test(expected = IllegalArgumentException.class)
    public void ctor_negativeSlots_Exception() {
        State state = new State(-1);
    }

    @Test
    public void ctor_withBlocks_stateMatches() {
        int[] slots = {1,5,2,3,7};
        State state = new State(slots);
        int[] actual = state.getSlots();
        Assert.assertArrayEquals(slots, actual);
    }

    @Test
    public void getSlots_returnsSlots() {
        State state = new State(3);
        int[] expected = new int[3];
        int[] actual = state.getSlots();

        Assert.assertArrayEquals(expected, actual);
    }

    @Test(expected = InvalidSlotException.class)
    public void addBlocksToSlot_invalidSlot_Exception() throws InvalidSlotException {
        State state = new State(3);
        state.addBlocksToSlot(4, 1);
    }

    @Test
    public void addBlocksToSlot_validSlot_stateMatches() throws InvalidSlotException {
        State state = new State(3);
        State newState = state.addBlocksToSlot(3, 1);
        int[] expected = {0,0,1};
        int[] actual = newState.getSlots();

        Assert.assertArrayEquals(expected, actual);
    }

    @Test(expected = InvalidSlotException.class)
    public void removeBlocksFromSlot_invalidSlot_Exception() throws InvalidSlotException, InsufficientBlocksException {
        State state = new State(3);
        State newState = state.removeBlocksFromSlot(4, 1);
    }

    @Test(expected = InsufficientBlocksException.class)
    public void removeBlocksFromSlot_notEnoughBlocks_Exception() throws InsufficientBlocksException, InvalidSlotException {
        State state = new State(3);
        State newState = state.removeBlocksFromSlot(1, 1);
    }

    @Test
    public void removeBlocksFromSlot_validArgs_stateMatches() throws InsufficientBlocksException, InvalidSlotException {
        State state = new State(3);
        State newState = state.addBlocksToSlot(3, 3)
                .removeBlocksFromSlot(3,1);
        int[] expected = {0,0,2};
        int[] actual = newState.getSlots();

        Assert.assertArrayEquals(expected, actual);
    }
}
