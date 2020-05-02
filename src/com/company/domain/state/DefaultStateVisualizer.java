package com.company.domain.state;

public final class DefaultStateVisualizer implements StateVisualizer {

    // This could have been done as part of the toString() of the State class but I was coding as if someone might
    // want to switch out visualizations
    public String visualize(final State state) {
        StringBuilder visualizedState = new StringBuilder();
        int[] slots = state.getSlots();
        for (int i = 0; i < slots.length; i++) {
            visualizedState.append(String.format("%d: ", i + 1));
            visualizedState.append("X".repeat(Math.max(0, slots[i])));
            visualizedState.append(System.lineSeparator());
        }

        return visualizedState.toString();
    }
}
