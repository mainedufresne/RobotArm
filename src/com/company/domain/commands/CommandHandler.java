package com.company.domain.commands;

import com.company.domain.commands.exceptions.InvalidCommandArgumentException;
import com.company.domain.commands.exceptions.InvalidCommandException;
import com.company.domain.state.State;

public interface CommandHandler {

    /**
     * Executes the provided command represented as a string
     *
     * @param rawCommand the string representation of the command and its arguments e.g. "add 2"
     * @return the new state after running the command
     * @throws InvalidCommandException if the command is invalid
     * @throws InvalidCommandArgumentException if any of the command arguments are invalid
     */
    State handle(String rawCommand) throws InvalidCommandException, InvalidCommandArgumentException;
}
