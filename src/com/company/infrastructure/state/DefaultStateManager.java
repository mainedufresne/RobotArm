package com.company.infrastructure.state;

import com.company.domain.history.HistoryManager;
import com.company.domain.state.State;
import com.company.domain.state.StateManager;

import java.util.List;
import java.util.Optional;

public final class DefaultStateManager implements StateManager {

    private final HistoryManager<State> stateHistory = new HistoryManager<>();

    @Override
    public Optional<State> getCurrentState() {
        if(stateHistory.getNumberOfEntries() == 0)
        {
            return Optional.empty();
        }
            return Optional.ofNullable(stateHistory.getHistory(1).get(0));
    }

    @Override
    public List<State> getAllState() {
        return stateHistory.getAll();
    }

    @Override
    public State setState(final State state) {
        if(state == null) {
            throw new IllegalArgumentException("Argument 'state' cannot be null");
        }

        stateHistory.push(state);
        return state;
    }
}
