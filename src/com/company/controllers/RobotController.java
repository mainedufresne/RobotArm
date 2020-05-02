package com.company.controllers;

public interface RobotController {

    /**
     * Instructs the robot to execute a command
     *
     * @param rawCommand the string representation of the command and its arguments e.g. "add 2"
     * @return a String representing the state of the system
     */
    String executeCommand(String rawCommand);
}
