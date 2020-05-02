package com.company.domain.history;

import com.company.domain.state.State;
import org.junit.Assert;
import org.junit.Test;

import java.util.List;

public class HistoryManagerTests {

    @Test(expected = IllegalArgumentException.class)
    public void push_nullState_Exception() {
        HistoryManager<State> history =  new HistoryManager<>();
        history.push(null);
    }

    @Test
    public void push_validState_TopOfHistoryMatches() {
         HistoryManager<State> history =  new HistoryManager<>();
        State expected = new State(3);
        history.push(new State(3));
        history.push(expected);
        State actual = history.getHistory(1).get(0);

        Assert.assertEquals(expected, actual);

    }

    @Test
    public void getHistory_noEntries_emptyList() {
         HistoryManager<State> history =  new HistoryManager<>();
        List<State> states = history.getHistory(1);

        Assert.assertTrue(states.isEmpty());
    }

    @Test
    public void getNumberOfEntries_empty_0() {
         HistoryManager<State> history =  new HistoryManager<>();
        Assert.assertEquals(0, history.getNumberOfEntries());
    }

    @Test
    public void getNumberOfEntries_notEmpty_correctNumber() {
         HistoryManager<State> history =  new HistoryManager<>();
        State expected = new State(3);
        history.push(new State(3));
        history.push(new State(3));

        Assert.assertEquals(2, history.getNumberOfEntries());
    }

    @Test
    public void getAll_empty_emptyList() {
         HistoryManager<State> history =  new HistoryManager<>();

        Assert.assertEquals(0, history.getAll().size());
    }

    @Test
    public void getAll_notEmpty_correctList() {
         HistoryManager<State> history =  new HistoryManager<>();
        State state1 = new State(3);
        State state2 = new State(8);

        history.push(state1);
        history.push(state2);

        List<State> actual = history.getAll();
        Assert.assertEquals(state2, actual.get(0));
        Assert.assertEquals(state1, actual.get(1));
        Assert.assertEquals(2, actual.size());
    }

    @Test
    public void getCurrent_empty_null() {
         HistoryManager<State> history =  new HistoryManager<>();

        Assert.assertNull(history.getCurrent());
    }

    @Test
    public void getCurrent_notEmpty_current() {
         HistoryManager<State> history = new HistoryManager<>();
        history.push(new State(3));
        State expected = new State(4);
        history.push(expected);

        Assert.assertEquals(expected, history.getCurrent());
    }
}
