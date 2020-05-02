package com.company.domain.commands;

import com.company.domain.commands.exceptions.InvalidCommandArgumentException;
import com.company.domain.commands.exceptions.InvalidCommandException;

public interface CommandParser {

    /**
     * Parses the string representation of the command and its arguments
     *
     * @param rawCommand the string representation of the command and its arguments e.g. "add 2"
     * @return the Command object with its arguments set
     * @throws InvalidCommandException if the command is invalid
     * @throws InvalidCommandArgumentException if any of the command's arguments are invalid
     */
    Command parseCommand(String rawCommand) throws InvalidCommandException, InvalidCommandArgumentException;

}
