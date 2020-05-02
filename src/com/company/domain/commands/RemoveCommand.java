package com.company.domain.commands;

import com.company.domain.commands.exceptions.InvalidCommandArgumentException;
import com.company.domain.commands.exceptions.InvalidCommandException;
import com.company.domain.history.HistoryManager;
import com.company.domain.state.State;
import com.company.domain.state.StateManager;
import com.company.domain.state.exceptions.InsufficientBlocksException;
import com.company.domain.state.exceptions.InvalidSlotException;

import java.util.List;

final class RemoveCommand extends Command {

    static final String NAME = "rm";
    private int slot;

    RemoveCommand(final HistoryManager<Command> commandHistory, final StateManager stateManager) {
        super(commandHistory, stateManager);
    }

    @Override
    void setArguments(final String... args) throws InvalidCommandArgumentException {
        assertStateExists();
        int slot;

        if (args.length != 1) {
            throw new InvalidCommandArgumentException("rm requires one integer argument");
        }

        this.slot = tryParseSlot(args[0]);
    }

    @Override
    State execute() {
        assertStateExists();
        return executeOnState(stateManager.getAllState());
    }

    @Override
    State executeOnState(List<State> state) {
        try {
            State currentState = state.get(0);
            return currentState.removeBlocksFromSlot(slot, 1);
        } catch (InvalidSlotException e) {
            throw new IllegalStateException("State changed between when argument was validated and command executed");
        } catch (InsufficientBlocksException e) {
            throw new InvalidCommandException(String.format("slot %d does not have any blocks to remove", slot));
        }
    }

}
