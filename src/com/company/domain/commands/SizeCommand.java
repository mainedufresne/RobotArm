package com.company.domain.commands;

import com.company.domain.commands.exceptions.InvalidCommandArgumentException;
import com.company.domain.history.HistoryManager;
import com.company.domain.state.State;
import com.company.domain.state.StateManager;

import java.util.Arrays;
import java.util.List;

final class SizeCommand extends Command {

    static final String NAME = "size";
    private int size;

    SizeCommand(final HistoryManager<Command> commandHistory, final StateManager stateManager) {
        super(commandHistory, stateManager);
    }

    @Override
    public void setArguments(final String[] args) throws InvalidCommandArgumentException {
        if (args.length != 1) {
            throw new InvalidCommandArgumentException("size requires one integer argument");
        }
        try {
            int size = Integer.parseInt(args[0]);
            if (size < 1) {
                throw new InvalidCommandArgumentException("size must be greater than 0");
            }
            this.size = size;
        } catch (NumberFormatException e) {
            throw new InvalidCommandArgumentException("size must be an integer");
        }
    }

    @Override
    public State execute() {
        return executeOnState(stateManager.getAllState());
    }

    @Override
    State executeOnState(List<State> state) {
        State updatedState;
        if (state.isEmpty()) {
            updatedState = new State(size);
        } else {
            State currentState = state.get(0);
            int[] oldSlots = currentState.getSlots();
            updatedState = new State(Arrays.copyOf(oldSlots, size));
        }
        return updatedState;
    }


}
