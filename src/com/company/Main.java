package com.company;

import com.company.controllers.DefaultRobotController;
import com.company.controllers.RobotController;
import com.company.domain.commands.*;
import com.company.domain.history.HistoryManager;
import com.company.domain.state.*;
import com.company.infrastructure.state.DefaultStateManager;

import java.util.Scanner;

public class Main {

    public static void main(String[] args) {

        //Setup application
        StateManager stateManager = new DefaultStateManager();
        StateVisualizer stateVisualizer = new DefaultStateVisualizer();
        HistoryManager<Command> commandHistoryManager = new HistoryManager<>();
        CommandParser commandParser = new DefaultCommandParser(commandHistoryManager, stateManager);
        CommandHandler commandHandler = new DefaultCommandHandler(commandParser, stateManager, commandHistoryManager);
        RobotController robotController = new DefaultRobotController(commandHandler, stateVisualizer);

        // create a scanner so we can read the command-line input
        Scanner scanner = new Scanner(System.in);

        //Event loop
        while(true) {
            System.out.print(">: ");
            String input = scanner.nextLine();
            if(input.equals("q")) {
                return;
            }
            System.out.println(robotController.executeCommand(input));
        }



    }
}
