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
 * Description: The main class for our Minesweeper app.
 * This is where the app is run.
 *
 * ****************************************
 */

package minesweepermvc;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import minesweepermvc.controller.MinesweeperController;
import minesweepermvc.model.MinesweeperModel;

import java.io.IOException;

/**
 * The main class for our Minesweeper app. This is where the app is run.
 */
public class MinesweeperMain extends Application {

    private MinesweeperModel theModel;
    private MinesweeperView theView;
    private MinesweeperController theController;

//    @Override
//    public void init() throws Exception {
//        super.init();
//        this.theModel = new MinesweeperModel(6, 6, 8);
//        this.theView = new MinesweeperView(this.theModel);
//    }

    @Override
    public void start(Stage primaryStage) throws IOException {
//        theModel = new MinesweeperModel(10, 15, 20);

        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("/minesweepermvc.fxml"));
        Parent root = loader.load();
        this.theView = loader.getController();
        this.theView.setModel(theModel);

        // Set up our stage
        primaryStage.setTitle("Minesweeper Game");
        primaryStage.setScene(new Scene(root));
        primaryStage.sizeToScene();
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}