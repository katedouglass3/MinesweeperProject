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
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Color;
import minesweepermvc.model.MinesweeperModel;

public class MinesweeperView {
    private MinesweeperModel theModel;
    private GraphicsContext gc;

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private Canvas canvas;

    @FXML
    private HBox topPane;

    public void setModel(MinesweeperModel theModel) {
        this.theModel = theModel;
    }

    @FXML
    void initialize() {
        assert canvas != null : "fx:id=\"canvas\" was not injected: check your FXML file 'minesweepermvc.fxml'.";
        assert topPane != null : "fx:id=\"topPane\" was not injected: check your FXML file 'minesweepermvc.fxml'.";

        this.gc = canvas.getGraphicsContext2D();

        theModel = new MinesweeperModel(10, 15, 20);
//        double width = canvas.getWidth() / theModel.getRowNumber();
//        double height = canvas.getHeight() / theModel.getColumnNumber();
        double size = 20;
        this.canvas.setWidth(theModel.getColumnNumber() * size);
        this.canvas.setHeight(theModel.getRowNumber() * size);
        for (int i = 0; i < theModel.getRowNumber(); i++){
            for (int j = 0; j < theModel.getColumnNumber(); j++) {
                gc.setFill(Color.BLACK);
                gc.fillRect(i * size, j * size, size, size);
                gc.setFill(Color.GREEN);
                gc.fillRect(i * size, j * size, size - 2, size - 2);
            }
        }

    }



}
