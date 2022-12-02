/* *****************************************
 * CSCI205 - Software Engineering and Design
 * Fall2022
 * Instructor: Prof. Brian King
 *
 * Team: Team 4
 * Date: 11/4/2022
 * Time: 9:27 AM
 *
 * Project: csci205_final_project
 * Package: minesweepermvc
 * Class: MinesweeperController
 *
 * Description: This is the MVC controller class for our Minesweeper app.
 * It contains any necessary event handlers for right clicks, left clicks,
 * and user selections of drop-downs and play again choices.
 *
 * ****************************************
 */

package minesweepermvc;

import javafx.application.Platform;
import javafx.scene.Node;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Tooltip;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseButton;
import javafx.scene.layout.StackPane;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Text;

import javax.swing.*;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

/**
 * This is the MVC controller class for our Minesweeper app. It contains any
 * necessary event handlers for right clicks, left clicks, and user selections
 * of drop-downs and play again choices.
 */
public class MinesweeperController {

    /** The view which sets up the scene graph for our application */
    private MinesweeperView theView;

    /** The model that contains the data and logic for our application */
    private MinesweeperModel theModel;

    /** A double array of cells representing the board */
    private Cell[][] board;

    /** A scheduled executor service for the timer thread */
    private ScheduledExecutorService timerThread;

    /** A scheduled executor service for displaying the bombs when the game is lost */
    private ScheduledExecutorService bombExecutor;

    /**
     * The constructor for the controller class that passes in instances of theModel
     * and theView and calls initBindings and initEventHandlers
     *
     * @param theView an instance of the minesweeper view class
     * @param theModel an instance of the minesweeper model class
     */
    public MinesweeperController(MinesweeperView theView, MinesweeperModel theModel) {
        this.theView = theView;
        this.theModel = theModel;

        initEventHandlers();
        initBindings();
    }

    /**
     * A method that creates bindings between the model cells and the view cells
     */
    private void initBindings() {
        // TODO: Update the color and value displayed on a cell whenever it is clicked

        // Create an instance of the view cell containers
        StackPane[][] cellContainers = theView.getCellContainers();
        // Create an instance of the model board
        Cell[][] cellModels = theModel.getBoard();

        // Loop through every row of cells
        for (int i = 0; i < cellContainers.length; i++) {
            // Loop through every cell in each row
            for (int j = 0; j < cellContainers[i].length; j++) {
                // Set the cell container to the cell in the correct row/column of the VIEW
                StackPane cellContainer = cellContainers[i][j];
                // Set the cell model to the cell in the correct row/column of the MODEL
                Cell cellModel = cellModels[i][j];
                // Add an ImageView to the cell container
                cellContainer.getChildren().add(new ImageView());

                // Loop through every child node in cell container
                for (Node child : cellContainer.getChildren()) {
                    // If the child is a rectangle, update its background color whenever there is a change
                    if (child instanceof Rectangle) {
                        Rectangle cellView = (Rectangle) child;
                        cellView.fillProperty().bind(cellModel.currentColorProperty());
                    }
                    // If the child is the text displayed on top of the rectangle, updates
                    // its value whenever there is a change
                    else if (child instanceof Text) {
                        Text value = (Text) child;
                        value.textProperty().bind(cellModel.displayValueProperty());
                    }
                    // TODO: finish commenting here
                    // If the child is an ImageView, bind it ???
                    else if (child instanceof ImageView) {
                        ImageView imageView = (ImageView) child;
                        imageView.imageProperty().bind(cellModel.imageValueProperty());
                        imageView.fitHeightProperty().bind(cellContainer.heightProperty());
                        imageView.fitWidthProperty().bind(cellContainer.widthProperty());
                    }
                }
            }
        }
    }

    /**
     * A method that handles left and right clicks for all cells and hovering over cells
     */
    private void initEventHandlers() {
        // Create an instance of the view cell containers
        StackPane[][] cellContainers = theView.getCellContainers();
        // Create an instance of the model board
        Cell[][] cellModels = theModel.getBoard();

        // Loop through every row of cells
        for (int i = 0; i < cellContainers.length; i++) {
            // Loop through every cell in each row
            for (int j = 0; j < cellContainers[i].length; j++) {
                // Set the cell container to the cell in the correct row/column of the view
                StackPane cellContainer = cellContainers[i][j];
                // Set the cell model to the cell in the correct row/column of the model
                Cell cellModel = cellModels[i][j];

                // When a cell is clicked
                int finalI = i;
                int finalJ = j;
                cellContainer.onMouseClickedProperty().setValue(event -> {
                    // If it is a left click
                    if (event.getButton() == MouseButton.PRIMARY) {
                        // If it is the first click of the game
                        if (theModel.getState() == GameState.NEW_GAME) {
                            // Move bombs so the first click is on a zero cell
                            theModel.regenerateAdjacentBombs(cellModel.getCellRowNumber(), cellModel.getCellColumnNumber());
                            // Add numbers to the cells without bombs
                            theModel.fillRemainingCells();
                            // Start the timer
                            theModel.getGameTimer().startTimer();
                            // Display the timer on the view
                            timerThread = Executors.newScheduledThreadPool(1);
                            timerThread.scheduleAtFixedRate(() -> theView.setLabelTimer(), 0, 1, TimeUnit.SECONDS);
                        }
                        // Call the left click method in Cell
                        cellModel.leftClick();
                        // Auto extend if a zero is opened
                        if (cellModel.getHiddenValue().equals("0"))
                            theModel.autoExtendCells(finalI, finalJ);
                        // Update the game state
                        theModel.checkIfGameOver();

                        // If the game is won or lost, create an appropriate popup
                        if (theModel.getState() == GameState.GAME_WON) {
                            // End the timer with game won
                            theModel.getGameTimer().endTimer(true);
                            displayAlert();
                        }
                        else if (theModel.getState() == GameState.GAME_LOST) {
                            // End the timer with game lost
                            theModel.getGameTimer().endTimer(false);
                            // When the game is lost, reveal each bomb one at a time
                            Runnable r = () -> {
                                for (Cell[] row : cellModels) {
                                    for (Cell cell : row) {
                                        if (!cell.isOpen() && cell.isBomb()) {
                                            cell.leftClick();
                                            return;
                                            } }
                                    } };
                            // Reveal 4 bombs every second (1 bomb every 250 ms)
                            bombExecutor = Executors.newScheduledThreadPool(1);
                            bombExecutor.scheduleAtFixedRate(r, 0, 250, TimeUnit.MILLISECONDS);

                            displayAlert();
                        }
                    }

                    // If it is a right click
                    else if (event.getButton() == MouseButton.SECONDARY) {
                        // Call the right click method in Cell
                        cellModel.rightClick();
                        theView.setLabelFlagsLeft();
                    }
                });

                // Handles Hovering over Cell
                // If the mouse enters a cell, make it brighter
                cellContainer.setOnMouseEntered(event -> {
                    if (!cellModel.isOpen())   // Only brighten cells that haven't been opened because only those can be clicked
                        cellModel.setCurrentColor(cellModel.getOriginalColor().brighter());
                    });
                // If a mouse leaves a cell, set back to original color
                cellContainer.setOnMouseExited(event -> {
                    if (!cellModel.isOpen())    // We don't want to revert any opened cells back to their unopened color
                        cellModel.setCurrentColor(cellModel.getOriginalColor());
                });
            }
        }
        // Create a tooltip for the instructions when the question mark is hovered over
        String instructions = "Minesweeper Instructions:\n\nGoal: Flag all of the bombs in the minefield\n\nHow To Play: " +
                "\n\t1.  Click anywhere on the board to start gameplay. " +
                "\n\t2.  Left click to open a cell, revealing the number of bombs it is touching, but be cautious because it could be a bomb! " +
                "\n\t3.  Right click to add a flag to cells you believe have bombs (right click again to remove the flag). At the top you can see how many flags are remaining! " +
                "\n\t4.  At the top of the screen you can see the elapsed time on the timer, so you can compete with yourself and others! " +
                "\n\t5.  You can also use the dropdowns to change the challenge level as well as the color scheme to your preference. " +
                "\n\nGood luck finding the bombs!";

        Tooltip.install(theView.getButtonInfo(), new Tooltip(instructions));

        // End the game and have the display bar pop up when the exit button is pressed
        theView.getButtonQuit().onMouseClickedProperty().setValue(event -> {
            theModel.setState(GameState.GAME_LOST);
            displayAlert();
        });

    }

    /**
     * A method that creates an alert for either winning or losing the game and
     * displays it when called. It then either resets the board or terminates the
     * program based on user input
     */
    private void displayAlert() {
        // Shut down the bomb thread and the timer thread if it is not null
        if (timerThread != null) {
            timerThread.shutdown();
        }
        // Create a play again button
        ButtonType playAgainBtn = new ButtonType("Play Again");
        // Create an alert with play again and exit as options
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION, "", playAgainBtn, new ButtonType("Exit"));
        alert.setTitle("Game Over");
        // Set the header to game lost or game won
        alert.setHeaderText(" " + theModel.getState());

        // Display the time and the best overall time
        alert.setContentText("Time: " + theModel.getGameTimer().getCurrentTime() + "\nBest Time: " + theModel.getGameTimer().getBestTime());
        // Display the alert and get the result of the button pushed
        // https://docs.oracle.com/javase/8/javafx/api/javafx/scene/control/Alert.html
        alert.showAndWait().ifPresent(response -> {
            // If the play again button is pressed, reset the board
            if (response.equals(playAgainBtn)) {
                // if the bomb executor thread has been started, shut it down
                if (bombExecutor != null) {
                    bombExecutor.shutdown();
                }
                resetGame();
            }
            // If exit is pressed, terminate the program
            else {
                System.exit(0);
            }
        });
    }

    /**
     * A method that resets the game play for when the user hits play again
     */
    private void resetGame() {
        // Reset the model
        theModel.resetBoard();
        // Clear the view
        theView.getRoot().getChildren().clear();
        // Add the top pane back to the root
        theView.getRoot().getChildren().add(theView.getTopPane());
        // Add the new model to the view
        theView.setModel(theModel);
        // Reset the flags remaining text
        theView.setLabelFlagsLeft();
        // Reset the game timer text
        theView.getLabelTimer().setText("0");
        // Set the controls for the new view
        initBindings();
        initEventHandlers();

        // Display the new board
        System.out.println();
        theModel.displayBoard();
    }
}
