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

package minesweepermvc.controller;

import javafx.scene.effect.Light;
import javafx.scene.shape.Circle;
import minesweepermvc.MinesweeperView;
import minesweepermvc.model.Cell;
import minesweepermvc.model.MinesweeperModel;

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

    public MinesweeperController(MinesweeperView theView, MinesweeperModel theModel) {
        this.theView = theView;
        this.theModel = theModel;

        initBindings();
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
}