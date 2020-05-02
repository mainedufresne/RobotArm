package com.company.infrastructure;

import com.company.domain.state.State;
import com.company.infrastructure.state.DefaultStateManager;
import org.junit.Assert;
import org.junit.Test;

import java.util.List;
import java.util.Optional;

public class DefaultStateManagerTests {

    @Test
    public void getCurrentState_noHistory_OptionalNotPresent() {
        DefaultStateManager defaultStateManager = new DefaultStateManager();
        Optional<State> state = defaultStateManager.getCurrentState();

        Assert.assertFalse(state.isPresent());
    }

    @Test
    public void getAllState_entriesInOrder() {
        DefaultStateManager defaultStateManager = new DefaultStateManager();
        State expected = new State(3);
        State initial = defaultStateManager.setState(new State(1));
        State middle = defaultStateManager.setState(new State(2));
        State current = defaultStateManager.setState(new State(3));

        List<State> previousStates = defaultStateManager.getAllState();

        Assert.assertEquals(current, previousStates.get(0));
        Assert.assertEquals(middle, previousStates.get(1));
        Assert.assertEquals(initial, previousStates.get(2));
    }

    @Test
    public void getAllState_noEntries_emptyList() {
        DefaultStateManager defaultStateManager = new DefaultStateManager();
        List<State> previousStates = defaultStateManager.getAllState();

        Assert.assertTrue(previousStates.isEmpty());

    }

    @Test(expected = IllegalArgumentException.class)
    public void setState_nullState_Exception() {
        DefaultStateManager defaultStateManager = new DefaultStateManager();
        defaultStateManager.setState(null);
    }

    @Test
    public void setState_validState_stateReturned() {
        DefaultStateManager defaultStateManager = new DefaultStateManager();
        State expected = new State(3);
        defaultStateManager.setState(new State(1));
        State actual = defaultStateManager.setState(expected);

        Assert.assertEquals(expected, actual);
    }

    @Test
    public void getState_stateSet_correctState() {
        DefaultStateManager defaultStateManager = new DefaultStateManager();
        State expected = new State(3);
        defaultStateManager.setState(new State(1));
        defaultStateManager.setState(expected);
        Optional<State> actual = defaultStateManager.getCurrentState();

        Assert.assertEquals(expected, actual.get());
    }
}
