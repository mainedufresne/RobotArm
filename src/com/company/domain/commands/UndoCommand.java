package com.company.domain.commands;

import com.company.domain.commands.exceptions.InvalidCommandArgumentException;
import com.company.domain.commands.exceptions.InvalidCommandException;
import com.company.domain.history.HistoryManager;
import com.company.domain.state.State;
import com.company.domain.state.StateManager;

import java.util.List;

/**
 * Command to undo previous commands. Undoes up to the number of commands requested.
 * It will not undo the original size command.
 */
final class UndoCommand extends Command {

    static final String NAME = "undo";
    private int commandsToUndo;

    UndoCommand(final HistoryManager<Command> commandHistory, final StateManager stateManager) {
        super(commandHistory, stateManager);
    }

    @Override
    void setArguments(String... args) throws InvalidCommandArgumentException {
        assertStateExists();
        if (args.length != 1) {
            throw new InvalidCommandArgumentException("undo requires one integer argument");
        }

        this.commandsToUndo = tryParseInt(args[0]);
    }

    @Override
    State execute() {
        assertStateExists();
        return executeOnState(stateManager.getAllState());
    }

    @Override
    State executeOnState(List<State> state) {
        if (commandsToUndo >= state.size()) {
            return state.get(state.size() - 1);
        }
        return state.get(commandsToUndo);
    }
}
