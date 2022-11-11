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

import javafx.scene.shape.Circle;
import javafx.scene.shape.Rectangle;

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
    private Cell[][] board;

    public MinesweeperController(MinesweeperView theView, MinesweeperModel theModel) {
        this.theView = theView;
        this.theModel = theModel;

        initBindings();
        initEventHandlers();
    }

    private void initBindings() {

//        // Bind the color of the light in the view to the model light color
//        for (int i = 0; i < theModel.getCells().size(); i++) {
//            Light lightModel = theModel.getLight(i);
//            Cell cellView = theView.getCell(i);
//
//            // Set up an event if the user clicks on a light
//            cellView.onMouseClickedProperty().setValue(event -> {
//                lightModel.toggle();
//                if (theModel.isIsAutoOff()) {
//                    lightModel.turnOffForMs(1000);
//                }
//            });
//        }

    }

    /**
     * A method that handles left and right clicks for all cells
     */
    private void initEventHandlers() {
        board = MinesweeperModel.getBoard();
        // For loop allows this eventHandler to function for all cells
        // For each row of cells
        for (Cell[] cellRow : board) {
            // For each cell in a row
            for (Cell cell : cellRow) {
                // Handles Clicking
                cell.onMouseClickedProperty().setValue(event ->
                {
                    // left click calls click method in Cell
                    if (event.isPrimaryButtonDown()) {
                        cell.click();
                    }
                    // right click calls rightClick() method in Cell
                    if (event.isSecondaryButtonDown()) {
                        cell.rightClick();
                    }
                });

                // Handles Hovering over Cell
                cell.setOnMouseEntered(event -> {
                    cell.setCurrentColor(cell.getCurrentColor().brighter());
                });





            }
        }
    }
}