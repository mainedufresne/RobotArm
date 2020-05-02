package com.company.domain.commands;

import com.company.domain.commands.exceptions.InvalidCommandArgumentException;
import com.company.domain.commands.exceptions.InvalidCommandException;
import com.company.domain.history.HistoryManager;
import com.company.domain.state.StateManager;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Supplier;

public final class DefaultCommandParser implements CommandParser {

    private Map<String, Supplier<Command>> commandMap = new HashMap<>();

    public DefaultCommandParser(final HistoryManager<Command> commandHistory, final StateManager stateManager) {
        if (commandHistory == null) {
            throw new IllegalArgumentException("Argument 'commandHistory' cannot be null");
        }
        if (stateManager == null) {
            throw new IllegalArgumentException("Argument 'stateManager' cannot be null");
        }

        commandMap.put(SizeCommand.NAME, () -> new SizeCommand(commandHistory, stateManager));
        commandMap.put(AddCommand.NAME, () -> new AddCommand(commandHistory, stateManager));
        commandMap.put(MoveCommand.NAME, () -> new MoveCommand(commandHistory, stateManager));
        commandMap.put(RemoveCommand.NAME, () -> new RemoveCommand(commandHistory, stateManager));
        commandMap.put(ReplayCommand.NAME, () -> new ReplayCommand(commandHistory, stateManager));
        commandMap.put(UndoCommand.NAME, () -> new UndoCommand(commandHistory, stateManager));
    }

    @Override
    public Command parseCommand(final String rawCommand) throws InvalidCommandException, InvalidCommandArgumentException {
        String[] tokens = rawCommand.split(" ");
        String command = tokens[0];
        String[] arguments = tokens.length > 1 ? Arrays.copyOfRange(tokens, 1, tokens.length) : new String[0];
        if(!commandMap.containsKey(command)) {
            throw new InvalidCommandException(buildErrorMessage(command));
        }
        Command resolvedCommand = commandMap.get(command).get();
        resolvedCommand.setArguments(arguments);
        return resolvedCommand;
    }

    private String buildErrorMessage(String command) {
        StringBuilder errorMessage = new StringBuilder();
        errorMessage.append(String.format("%s is an invalid command. Options are:%n", command));
        commandMap.keySet().forEach(c -> errorMessage.append(String.format("%s%n", c)));
        return errorMessage.toString();
    }
}
