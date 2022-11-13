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
 * Description: This is the "view" in the MVC design for our Minesweeper app. The view class
 * initializes all nodes for the scene graph to display for a good GUI design
 *
 * ****************************************
 */

package minesweepermvc;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.geometry.Pos;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Text;

public class MinesweeperView {
    private MinesweeperModel theModel;

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private VBox root;

    @FXML
    private HBox topPane;

    // An array of Rectangles, each rectangle represents a cell in the board
    private Rectangle[][] cells;

    // An array of StackPanes, each StackPane represents contain a Rectangle and the text
    // representing its value
    private StackPane[][] cellContainers;

    public StackPane[][] getCellContainers() {
        return cellContainers;
    }

    public Rectangle[][] getCells() {
        return cells;
    }

    public void setModel(MinesweeperModel theModel) {
        this.theModel = theModel;
        initBoardPane();
    }

    @FXML
    void initialize() {
        assert root != null : "fx:id=\"root\" was not injected: check your FXML file 'minesweepermvc.fxml'.";
        assert topPane != null : "fx:id=\"topPane\" was not injected: check your FXML file 'minesweepermvc.fxml'.";

    }

    // Set up the board pane, which contains all the cells
    public void initBoardPane() {
        cells = new Rectangle[theModel.getRowNumber()][theModel.getColumnNumber()];
        cellContainers = new StackPane[theModel.getRowNumber()][theModel.getColumnNumber()];
        // Set the size of each square
        double size = 40;
        for (int i = 0; i < theModel.getRowNumber(); i++) {
            for (int j = 0; j < theModel.getColumnNumber(); j++) {
                Cell cellModel = theModel.getBoard()[i][j];
                Rectangle cellView = new Rectangle(i * size, j * size, size, size);
                // Set the color of each cell based on its position
                if ((i + j) % 2 == 0) {
                    cellModel.setCurrentColor(cellModel.lightGreen);
                }
                else {
                    cellModel.setCurrentColor(cellModel.darkGreen);
                }

                // Color each Rectangle and add it to the array of Rectangles
                cellView.setFill(cellModel.getCurrentColor());
                cells[i][j] =  cellView;
                // For each cell, we set up a StackPane that wraps the Rectangle and the text inside
                // that rectangle
                StackPane cellContainer = new StackPane();
                Text value = new Text(cellModel.getDisplayValue());
                // Add Rectangle and the text to cellContainer
                cellContainer.getChildren().add(cellView);
                cellContainer.setAlignment(cellView, Pos.CENTER);
                cellContainer.getChildren().add(value);
                StackPane.setAlignment(value, Pos.CENTER);
                cellContainers[i][j] = cellContainer;
            }
        }

        for (int i = 0; i < cellContainers.length; i++) {
            HBox row = new HBox();
            for (int j = 0; j < cellContainers[i].length; j++) {
                row.getChildren().add(cellContainers[i][j]);
            }
            root.getChildren().add(row);
        }
    }

}
