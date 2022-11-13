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

import java.io.IOException;

/**
 * The main class for our Minesweeper app. This is where the app is run.
 */
public class MinesweeperMain extends Application {

    private MinesweeperModel theModel;
    private MinesweeperView theView;
    private MinesweeperController theController;

    @Override
    public void start(Stage primaryStage) throws IOException {
        initModel();

        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("/minesweepermvc.fxml"));
        Parent root = loader.load();
        this.theView = loader.getController();
        this.theView.setModel(theModel);

        this.theController = new MinesweeperController(this.theView, this.theModel);

        // Set up our stage
        primaryStage.setTitle("Minesweeper Game");
        primaryStage.setScene(new Scene(root));
        primaryStage.setResizable(false);
        primaryStage.sizeToScene();
        primaryStage.show();

        // Print key in the console
        theModel.displayBoard();
    }

    // A separate function to initialize the model only
    public void initModel() {
        theModel = new MinesweeperModel(10, 15, 20);
        theModel.createCompleteModel();
    }

    public static void main(String[] args) {
        launch(args);
    }
}