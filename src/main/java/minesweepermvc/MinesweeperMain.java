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

    /** An instance of MinesweeperModel */
    private MinesweeperModel theModel;
    /** An instance of MinesweeperView */
    private MinesweeperView theView;
    /** An instance of MinesweeperController */
    private MinesweeperController theController;

    /**
     * The main entry point for all JavaFX applications.
     * The start method is called after the init method has returned,
     * and after the system is ready for the application to begin running.
     */
    @Override
    public void start(Stage primaryStage) throws IOException {
        initModel();

        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("/minesweepermvc.fxml"));
        loader.setLocation(getClass().getResource("/minesweeperTopPane.fxml"));
        Parent root = loader.load();
        this.theView = loader.getController();
        this.theView.setModel(theModel);
        this.theView.setTopPane();

        this.theController = new MinesweeperController(this.theView, this.theModel);

        // Create the scene and add styling in CSS
        Scene scene = new Scene(root);

        // Set up our stage
        primaryStage.setTitle("Minesweeper Game");
        primaryStage.setScene(scene);
        primaryStage.setResizable(false);
        primaryStage.sizeToScene();
        primaryStage.show();


         //Print key in the console
         theModel.displayBoard();
    }

    /**
     * The application initialization method. This method is called immediately
     * after the Application class is loaded and constructed, but before the
     * start() method is invoked.
     */
    // A separate function to initialize the model only
    public void initModel() {
        theModel = new MinesweeperModel(10, 15, 20);
        theModel.createCompleteModel();
    }

    /**
     * Our standard main program for a JavaFX application
     *
     * @param args - the command line prompts
     */
    public static void main(String[] args) {
        launch(args);
    }
}