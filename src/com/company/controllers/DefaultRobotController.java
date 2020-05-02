package com.company.controllers;

import com.company.domain.commands.CommandHandler;
import com.company.domain.commands.exceptions.InvalidCommandArgumentException;
import com.company.domain.commands.exceptions.InvalidCommandException;
import com.company.domain.state.State;
import com.company.domain.state.StateVisualizer;

public final class DefaultRobotController implements RobotController {

    private final StateVisualizer stateVisualizer;
    private final CommandHandler commandHandler;

    public DefaultRobotController(CommandHandler commandHandler, StateVisualizer stateVisualizer) {
        if (commandHandler == null) {
            throw new IllegalArgumentException("Argument 'commandHandler' cannot be null");
        }
        if (stateVisualizer == null) {
            throw new IllegalArgumentException("Argument 'stateVisualizer' cannot be null");
        }
        this.commandHandler = commandHandler;
        this.stateVisualizer = stateVisualizer;
    }

    @Override
    public String executeCommand(String rawCommand) {
        String errorMessage;
        try {
            State updateState = commandHandler.handle(rawCommand);
            return stateVisualizer.visualize(updateState);

        } catch (InvalidCommandException | InvalidCommandArgumentException e) {
            errorMessage = e.getMessage();
        }

        return String.format("%s ", errorMessage);
    }
}
