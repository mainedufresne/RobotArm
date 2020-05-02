# RobotArm

Goal: Design a command-line controller program for a robotic arm that takes commands that move blocks stacked in a series of slots. After each command, output the state of the slots, which each line of output corresponding to a slot and each X corresponding to a block.
      
Commands:
      
      size [n] - Adjusts the number of slots, resizing if necessary. Program must start with this to be valid.
      add [slot] - Adds a block to the specified slot.
      mv [slot1] [slot2] - Moves a block from slot1 to slot2.
      rm [slot] - Removes a block from the slot.
      replay [n] - Replays the last n commands.
      undo [n] - Undo the last n commands.
      
Your program should validate that the commands are syntactically valid before executing them.

Example:
      
      > size 3
      1:
      2:
      3:
      
       > add 2
      1:
      2: X
      3:
      
       > add 3
      1:
      2: X
      3: X
      
       > mv 3 2
      1:
      2: XX
      3:
      
       > rm 2
      1:
      2: X
      3:
      
Assumptions:

Input:
- I didn't worry about the cursor position.
- Included 'q' as a way to quit

Testing:
- I started off with TDD but a main benefit of testing is to ensure one doesn't break things when modifying them.
So the amount of automated tests should be be greater the more the code changes. Since this program is a one off, and time
sensitive, I stopped adding tests after state was completed.

Commands: 
- I felt that replay and undo should really be "up to the last [n] commands" to save the user from having to know 
exactly how many previous commands had been issued. Could have also returned an error but felt as a user that would
annoy me. 
 
State:
- Adding and Removing multiple blocks seemed like it would be an inevitable request and did not require extra effort.
- I might break up the StateManager into a Reader and Writer. Commands probably shouldn't have write access.
- I didn't implement for any sort of thread safety in this project

Building and Running the old school way (assuming *nix):
    
    mkdir bin
    javac -d bin -cp src/ src/com/company/Main.java 
    cd bin/
    jar -cvfe Robot.jar com.company.Main .
    java -jar Robot.jar 

