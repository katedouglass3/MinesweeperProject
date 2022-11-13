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
import javafx.scene.input.MouseButton;
import javafx.scene.layout.StackPane;
import javafx.scene.shape.Circle;
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
    private Cell[][] board;

    public MinesweeperController(MinesweeperView theView, MinesweeperModel theModel) {
        this.theView = theView;
        this.theModel = theModel;

        initBindings();
        initEventHandlers();
    }

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

                cellContainer.onMouseClickedProperty().setValue(event -> {
                    if (event.getButton() == MouseButton.PRIMARY) {

                        cellModel.leftClick();
                    } else if (event.getButton() == MouseButton.SECONDARY)
                        cellModel.rightClick();
                });
            }
        }
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
                        cell.leftClick();
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