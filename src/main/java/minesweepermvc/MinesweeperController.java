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
 * Class: MinesweeperMain
 *
 * Description: This is the MVC controller class
 * for our Minesweeper app. It contains any necessary
 * event handlers for right clicks, left clicks, and user
 * selections of drop-downs and play again choices.
 *
 * ****************************************
 */

package minesweepermvc;

import javafx.scene.Node;
import javafx.scene.control.Alert;
import javafx.scene.input.MouseButton;
import javafx.scene.layout.StackPane;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Text;

/**
 * This is the MVC controller class
 * for our Minesweeper app. It contains any necessary
 * event handlers for right clicks, left clicks, and user
 * selections of drop-downs and play again choices.
 */
public class MinesweeperController {

    /** The view which sets up the scene graph for our application */
    private MinesweeperView theView;

    /** The model that contains the data and logic for our application */
    private MinesweeperModel theModel;

    /** A double array of cells representing the board */
    private Cell[][] board;

    /** An instance of GameTimer */
    private GameTimer gameTimer;

    /**
     * The constructor for the controller class that creates instances of theModel and theView
     * and calls initBindings and initEventHandlers
     *
     * @param theView an instance of the minesweeper view class
     * @param theModel an instance of the minesweeper model class
     */
    public MinesweeperController(MinesweeperView theView, MinesweeperModel theModel) {
        this.theView = theView;
        this.theModel = theModel;
        this.gameTimer = new GameTimer();

        initEventHandlers();
        initBindings();
    }

    /**
     * A method that creates bindings between the model cells and the view cells
     */
    private void initBindings() {
        // Update the color and value displayed on a cell whenever it is clicked
        StackPane[][] cellContainers = theView.getCellContainers();
        Cell[][] cellModels = theModel.getBoard();
        for (int i = 0; i < cellContainers.length; i++) {
            for (int j = 0; j < cellContainers[i].length; j++) {
                StackPane cellContainer = cellContainers[i][j];
                Cell cellModel = cellModels[i][j];

                for (Node child : cellContainer.getChildren()) {
                    if (child instanceof Rectangle) {
                        Rectangle cellView = (Rectangle) child;
                        cellView.fillProperty().bind(cellModel.currentColorProperty());
                    } else if (child instanceof Text) {
                        Text value = (Text) child;
                        value.textProperty().bind(cellModel.displayValueProperty());
                    }
                }
            }
        }
    }

    /**
     * A method that handles left and right clicks for all cells and hovering  over cells
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
                cellContainer.onMouseClickedProperty().setValue(event -> {
                    // If it is a left click
                    if (event.getButton() == MouseButton.PRIMARY) {
                        // If it is the first click of the game
                        if (theModel.getState() == GameState.NEW_GAME) {
                            // Move bombs so the first click is on a zero cell
                            // theModel.regenerateAdjacentBombs(cellModel.getCellRowNumber(), cellModel.getCellColumnNumber());
                            // Start the timer
                            gameTimer.startTimer();
                        }
                        // Call the left click model in Cell
                        cellModel.leftClick();
                        // Update the game state
                        theModel.checkIfGameOver();
                        // If the game is won or lost, create an appropriate popup
                        if (theModel.getState() == GameState.GAME_WON || theModel.getState() == GameState.GAME_LOST) {
                            Alert alert = new Alert(Alert.AlertType.INFORMATION);
                            alert.setTitle("Game Over");
                            alert.setHeaderText(" " + theModel.getState());
                            alert.show();
                        }
                    }
                    // If it is a right click
                    else if (event.getButton() == MouseButton.SECONDARY) {
                        // Call the right click method in Cell
                        cellModel.rightClick();
                    }
                });
//
//                // Handles Hovering over Cell
//                // If the mouse enters a cell, make it brighter
//                cellContainer.setOnMouseEntered(event -> {
//                    cellModel.setCurrentColor(cellModel.getCurrentColor().brighter());
//                });
//                // If a mouse leaves a cell, make it darker
//                cellContainer.setOnMouseExited(event -> {
//                    cellModel.setCurrentColor(cellModel.getCurrentColor().darker());
//                });
            }
        }

    }
}