package com.company.domain.state;

import java.util.List;
import java.util.Optional;

public interface StateManager {

    /**
     * Gets the current state as an Optional. It should be checked as no state may have been set.
     *
     * @return An Optional of the current state
     */
    Optional<State> getCurrentState();

    /**
     * gets a complete list of all the states
     *
     * @return a list of all the states in order from most to least recent
     */
    List<State> getAllState();

    /**
     * Sets the current state
     *
     * @param state the state to set as current
     * @return the current state. Useful for chaining.
     */
    State setState(State state);

}
