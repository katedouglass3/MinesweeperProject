# CSCI 205 - Software Engineering and Design
Bucknell University  
Lewisburg, PA

### Course Info
Instructor: Prof. King  
Semester: Fall 2022

## Team 04 Information
Team Members: Clara Chaplin (Scrum Master), Vy Tran (Developer), Kate Douglass (Project Owner), Kona Glenn (Developer), & Marion Duval (Developer)  

Marion is a sophomore Cell Biology/Biochemistry major and Computer Science minor from Middleton, Massachusetts.  
Kate is a sophomore Computer Science and Engineering major from West Chester, Pennsylvania.  
Clara is a sophomore Mathematics and Computer Science double major from Albany, NY.   
Kona is a sophomore Computer Science and Engineering and Applied Math double major.  
Vy is a sophomore Computer Science major.

## Project Information
Our team is creating a Minesweeper game with a variety of settings featured in a user-friendly GUI. 
Features will include different color themes, game board sizes, and difficulty levels. These will
be selectable by using a drop-down menu. A timer will begin when the player makes the first move, tracking
how long the player has been playing the game. The number of flags remaining will also be displayed.
Users will be able to quit or reset the game at any point that a game is in progress. Previous attempts will be
recorded, and players can try and beat their previous fastest time. Once the game is complete, the user will
see their final time and their fastest time, and can choose to play again.

## How to run it
To run our Minesweeper game, run MinesweeperMain.main(). Next, a medium-size board will appear
on the screen. The user can then choose between different game board sizes on one drop down and 
different color modes on another drop down. Then, the user should click any cell to start the game.
The number in a cell represents the number of neighboring bombs (vertically, horizontally, or diagonally).
The goal is to flag any cells that contain a bomb by right-clicking, and left click on any cells that do not 
have a bomb to display the value of the cell. If the user successfully clicks all the bomb-free cells without
clicking any with bombs, they win!

Whenever a user wins, clicks on a bomb, or clicks quit, a pop-up appears, displaying the time they took to complete
their last game along with their best time. The user can then click play again to reset the board or click quit to
exit the app.

## Package Structure & Libraries
Within the first level of our csci205_final_project folder, we have our README.md, our java folder, our resources
folder, and our test folder. All the .git and .gradle files are also found on this level. Finally, we have the 
design folder holding the pdfs of all of our diagrams and our docs folder holding our scrum report,
design manual, user manual, and our PowerPoint presentation.

Within the java folder, we have a minesweepermvc folder that holds all of our classes. There is a model folder within
the minesweepermvc folder that holds our MinesweeperModel, along with our Cell class, GameTimer class, GameState enum, 
and ColorMode enum. We also have our MinesweeperMain class, MinesweeperController class, and our MinesweeperView class
within the minesweepermvc folder. Within the test folder, we have tests for our Cell class, our MinesweeperModel class, 
and our GameTimer class. Within the resources folder, we have images of a bomb, a clock, and a red flag, along with our
FXML files for the board and for the top pane. Finally, we have an instructions.css file to design our instructions pop-up.

External Libraries: JavaFX Version 17

## Video URL
A link to our video on mediaspace: https://mediaspace.bucknell.edu/media/Team+4+CSCI205+Project+Video/1_iqxzzopt.