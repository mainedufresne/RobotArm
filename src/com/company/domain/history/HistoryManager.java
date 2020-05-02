package com.company.domain.history;

import java.util.LinkedList;
import java.util.List;

public final class HistoryManager<T> {

    private final List<T> history = new LinkedList<>();

    /**
     * Pushes an Entry into the history
     * @param historyEntry the entry to log in the history
     */
    public void push(final T historyEntry) {
        if(historyEntry == null) {
            throw new IllegalArgumentException("Argument 'historyEntry' cannot be null");
        }
        history.add(0, historyEntry);
    }

    /**
     * If getLast is going to throw an exception for exceeding the number of entries, it seems prudent
     * to give the caller a mechanism by which to check how many entries there are.
     * @return the number of entries in the history
     */
    public int getNumberOfEntries() {
        return history.size();
    }

    /**
     * Gets the most recent entry e.g. the current one
     * @return the most recent entry
     */
    public T getCurrent() {
        return history.isEmpty() ? null : history.get(0);
    }

    /**
     * Retrieve up to the specified number of entries from the history from newest to oldest.
     * @param numberOfEntries the number of entries to retrieve. If this number exceeds the number of entries the entire
     *                        history will be returned.
     * @return a list of entries in order from most to least recent.
     * history
     */
    public List<T> getHistory(final int numberOfEntries) {
        if(numberOfEntries >= getNumberOfEntries() - 1) {
            return List.copyOf(history);
        }
        return List.copyOf(history.subList(0, numberOfEntries)); //sublist returns a view not a new list
    }

    /**
     * Gets all available history entries
     * @return a list of all history entries in order from most to least recent
     */
    public List<T> getAll() {
        return List.copyOf(history);
    }
}
