package com.company.domain.state;

public interface StateVisualizer {

    /**
     * Visualizes the supplied State
     * @param state the state to visualize
     * @return a string representation of the state
     */
    String visualize(State state);

}
