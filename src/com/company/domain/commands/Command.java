package com.company.domain.commands;

import com.company.domain.commands.exceptions.InvalidCommandArgumentException;
import com.company.domain.commands.exceptions.InvalidCommandException;
import com.company.domain.history.HistoryManager;
import com.company.domain.state.State;
import com.company.domain.state.StateManager;

import java.util.List;

/**
 * The abstract class for all Commands
 */
public abstract class Command {

    final HistoryManager<Command> commandHistory;
    final StateManager stateManager;

    Command(HistoryManager<Command> commandHistory, StateManager stateManager) {
        if (commandHistory == null) {
            throw new IllegalArgumentException("Argument 'commandHistory' cannot be null");
        }
        if (stateManager == null) {
            throw new IllegalArgumentException("Argument 'stateManager' cannot be null");
        }

        this.commandHistory = commandHistory;
        this.stateManager = stateManager;
    }

    /**
     * Sets the arguments required to execute the command
     *
     * @param args the arguments required for the command, must be called before calling execute method
     * @throws InvalidCommandArgumentException if any of the arguments are invalid
     */
    abstract void setArguments(String... args) throws InvalidCommandArgumentException;

    /**
     * Executes the command. Arguments should be validated before this method is called
     *
     * @return the updated state after executing the command
     */
    abstract State execute();

    /**
     * executes the command on the provided state
     *
     * @param state the state on which to run the command
     * @return the final calculated state
     */
    abstract State executeOnState(List<State> state);

    void assertStateExists() {
        if (stateManager.getCurrentState().isEmpty()) {
            throw new InvalidCommandException(String.format("State must be initialized by calling 'size' first%n"));
        }
    }

    /**
     * Tries to parse the slot number from the provided string. assertStateExists should be called prior to this method.
     *
     * @param arg the string from which to parse the slot number
     * @return the slot number
     * @throws InvalidCommandArgumentException if the argument cannot be parsed or is an invalid slot
     */
    int tryParseSlot(String arg) throws InvalidCommandArgumentException {
        int slot = tryParseInt(arg);
        int numSlots = stateManager.getCurrentState().get().getSlots().length;
        if (slot > numSlots) {
            throw new InvalidCommandArgumentException(String.format("Invalid slot %d; must be between 1 and %d", slot, numSlots));
        }

        return slot;
    }

    /**
     * Tries to parse an Int from a string and wraps the exception if it fails
     *
     * @param arg the integer to parse
     * @return the integer representation of the string
     * @throws InvalidCommandArgumentException if the integer could not be parsed
     */
    int tryParseInt(String arg) throws InvalidCommandArgumentException {
        int amount;
        try {
            amount = Integer.parseInt(arg);
        } catch (NumberFormatException e) {
            throw new InvalidCommandArgumentException("must be an integer");
        }

        return amount;
    }
}
