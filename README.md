# Snake Game in Java
This repository contains the Java implementation of the classic Snake game. The game is designed following the Model-View-Controller (MVC) architectural pattern, ensuring a clear separation between the game's logic, user interface, and control mechanisms. This structure enhances maintainability and scalability of the code.

## Prerequisites
_Before you compile and run the Snake game, ensure you have the following installed on your system:_

Java Development Kit (JDK) 8 or higher
You can check your Java version by opening a terminal (Command Prompt, PowerShell, or any Unix-based terminal) and typing:

``` 
java -version
```


## Compilation
The game consists of multiple Java files that need to be compiled before running the game. To compile the game, navigate to the project's root directory in your terminal and execute the following command:

``` 
javac *.java
``` 
This command compiles all the .java files in the directory. Ensure you're in the correct directory where the game's source files are located before running the command.

## Running the Game
After compiling the source files, you can run the game by executing the main class. The main class file is named Main.java. To run the game, use the following command in the terminal:

``` 
java Main
``` 


## Game Controls
The game is played using the arrow keys:

Up Arrow - Move the snake up
Down Arrow - Move the snake down
Left Arrow - Move the snake left
Right Arrow - Move the snake right

Your objective is to eat as many dollars as possible to increase your score. Be careful not to run into the walls or into the snake's own body.

## Architecture Overview
This Snake game follows the MVC architecture, divided into three main components:

Model: Contains all the logic and data structures for the game state, such as the snake's position, direction, the location of the apple, and the score.
View: Responsible for rendering the game graphics on the screen, updating the display based on the current game state.
Controller: Handles user input (keyboard events) and updates the model accordingly. It acts as an intermediary between the Model and View, updating the game state and view in response to user actions.


## Contributing
Contributions to the Snake game are welcome! Please feel free to fork the repository, make your changes, and submit a pull request.

Enjoy playing and enhancing the Snake game! 🐍🍎
