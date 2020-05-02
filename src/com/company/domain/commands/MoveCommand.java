package com.company.domain.commands;

import com.company.domain.commands.exceptions.InvalidCommandArgumentException;
import com.company.domain.commands.exceptions.InvalidCommandException;
import com.company.domain.history.HistoryManager;
import com.company.domain.state.State;
import com.company.domain.state.StateManager;
import com.company.domain.state.exceptions.InsufficientBlocksException;
import com.company.domain.state.exceptions.InvalidSlotException;

import java.util.List;

final class MoveCommand extends Command {

    static final String NAME = "mv";
    private int fromSlot;
    private int toSlot;

    MoveCommand(final HistoryManager<Command> commandHistory, final StateManager stateManager) {
        super(commandHistory, stateManager);
    }

    @Override
    void setArguments(final String... args) throws InvalidCommandArgumentException {
        assertStateExists();

        if (args.length != 2) {
            throw new InvalidCommandArgumentException("mv requires two integer arguments");
        }

        this.fromSlot = tryParseSlot(args[0]);
        this.toSlot = tryParseSlot(args[1]);
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
            return currentState.removeBlocksFromSlot(fromSlot, 1)
                    .addBlocksToSlot(toSlot, 1);
        } catch (InvalidSlotException e) {
            throw new IllegalStateException("State changed between when argument was validated and command executed");
        } catch (InsufficientBlocksException e) {
            throw new InvalidCommandException(String.format("slot %d does not have any blocks to move", fromSlot));
        }
    }
}
