package com.company.domain.commands;

import com.company.domain.commands.exceptions.InvalidCommandArgumentException;
import com.company.domain.commands.exceptions.InvalidCommandException;
import com.company.domain.history.HistoryManager;
import com.company.domain.state.State;
import com.company.domain.state.StateManager;

import java.util.LinkedList;
import java.util.List;
import java.util.Stack;

final class ReplayCommand extends Command {

    static final String NAME = "replay";
    private int numberOfCommandsToReplay;
    private List<Command> commandsRun; // Remember what commands this replayed so they can be replayed again

    ReplayCommand(final HistoryManager<Command> commandHistory, final StateManager stateManager) {
        super(commandHistory, stateManager);
    }

    @Override
    void setArguments(final String... args) throws InvalidCommandArgumentException {
        assertStateExists();
        if (args.length != 1) {
            throw new InvalidCommandArgumentException("replay requires one integer argument");
        }

        numberOfCommandsToReplay = tryParseInt(args[0]);
        if (numberOfCommandsToReplay < 1) {
            throw new InvalidCommandArgumentException("replay amount must be greater than 0");
        }
    }

    @Override
    State execute() {
        assertStateExists();
        return executeOnState(stateManager.getAllState());
    }

    @Override
    State executeOnState(final List<State> state) {
        List<Command> commandsToReplay;
        if (commandsRun != null) {
            commandsToReplay = commandsRun;
        } else {
            commandsToReplay = commandHistory.getHistory(numberOfCommandsToReplay);
            commandsRun = List.copyOf(commandsToReplay);
        }

        Stack<Command> commandsToExecute = new Stack<>();
        commandsToReplay.forEach(commandsToExecute::push);

        State finalState = state.get(0);
        List<State> mutableState = new LinkedList<>(state) {
        };
        while (!commandsToExecute.empty()) {
            finalState = commandsToExecute.pop().executeOnState(mutableState);
            mutableState.add(0, finalState);
        }

        return finalState;
    }
}
