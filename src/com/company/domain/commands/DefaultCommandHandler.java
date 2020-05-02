package com.company.domain.commands;

import com.company.domain.commands.exceptions.InvalidCommandArgumentException;
import com.company.domain.commands.exceptions.InvalidCommandException;
import com.company.domain.history.HistoryManager;
import com.company.domain.state.State;
import com.company.domain.state.StateManager;

public final class DefaultCommandHandler implements CommandHandler {

    private final CommandParser commandParser;
    private final StateManager stateManager;
    private final HistoryManager<Command> commandHistory;

    public DefaultCommandHandler(final CommandParser commandParser, final StateManager stateManager, HistoryManager<Command> commandHistory) {
        if (commandParser == null) {
            throw new IllegalArgumentException("Argument 'commandParser' cannot be null");
        }
        if (stateManager == null) {
            throw new IllegalArgumentException("Argument 'stateManager' must not be null");
        }
        if (commandHistory == null) {
            throw new IllegalArgumentException("Argument 'commandHistory' must not be null");
        }

        this.commandParser = commandParser;
        this.stateManager = stateManager;
        this.commandHistory = commandHistory;
    }

    @Override
    public State handle(final String rawCommand) throws InvalidCommandException, InvalidCommandArgumentException {
        if (rawCommand == null) {
            throw new IllegalArgumentException("Argument 'command' cannot be null");
        }

        Command resolvedCommand = commandParser.parseCommand(rawCommand);
        State nextState = resolvedCommand.execute();
        stateManager.setState(nextState);
        commandHistory.push(resolvedCommand);
        return stateManager.getCurrentState().orElseThrow(IllegalStateException::new);
    }
}
