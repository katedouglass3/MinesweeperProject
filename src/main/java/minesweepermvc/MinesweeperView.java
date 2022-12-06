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

import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.geometry.Pos;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Text;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import minesweepermvc.model.Cell;
import minesweepermvc.model.MinesweeperModel;

/**
 * This is the "view" in the MVC design for our Minesweeper app. The view class
 * initializes all nodes for the scene graph to display for a good GUI design
 */
public class MinesweeperView {
    /** The LCM of the column sizes */
    public static final int BOARD_WIDTH = 720;
    /** The LCM of the row sizes */
    public static final int BOARD_HEIGHT = 560;

    /** An instance of MinesweeperModel */
    private MinesweeperModel theModel;

    /** FXML instance of VBox */
    @FXML
    private VBox root;

    /** FXML instance of HBox */
    @FXML
    private HBox topPane;

    /** A double array of Rectangles, each rectangle represents a cell in the board */
    private Rectangle[][] cells;

    /**
     * A double array of StackPanes, each StackPane represents contain a Rectangle and the text
     * representing its value
     */
    private StackPane[][] cellContainers;

    private String currentChallengeLevel = "Regular";

    /** A button the user can press to get the game's instructions */
    @FXML
    private Button buttonInfo;

    /** A button the user can press to quit the game */
    @FXML
    private Button buttonQuit;

    /** A choice box of which the user can change the challenge level */
    @FXML
    private ChoiceBox<String> choiceChallengeLevel;

    /** A choice box of which the user can change the color mode */
    @FXML
    private ChoiceBox<String> choiceColorMode;

    /** The image of the flag to signify the number of flags left */
    @FXML
    private ImageView imageFlag;

    /** The image of the timer to signify the duration of gameplay */
    @FXML
    private ImageView imageTimer;

    /** A Label that shows the number of flags left */
    @FXML
    private Label labelFlagsLeft;

    /** A label that shows the number of seconds since the game has started */
    @FXML
    private Label labelTimer;

    /** A label that says "Minesweeper" */
    @FXML
    private Label labelTitle;

    /**
     * A getter method that returns the double array of StackPanes that holds the Cells from view
     *
     * @return - a double array of StackPanes
     */
    public StackPane[][] getCellContainers() {
        return cellContainers;
    }

    /**
     * A getter method for root that returns the VBox that holds the root
     *
     * @return root - a VBox holding all the cells
     */
    public VBox getRoot() {
        return root;
    }

    /**
     * A getter method for the topPane that returns the HBox that holds the topPane
     *
     * @return topPane - a HBox holding the top display bar
     */
    public HBox getTopPane() {
        return topPane;
    }


    /**
     * A setter method that sets theModel instance variable to the MinesweeperModel instance
     * and sets up the board of cells and the top pane's drop-downs
     *
     * @param theModel - the instance of MinesweeperModel in the View class
     */
    public void setModel(MinesweeperModel theModel) {
        this.theModel = theModel;
        initBoardPane();
    }

    /**
     * A setter method that sets the top[ pane by adding all the different features created
     * in initChoiceBoxes
     */
    public void initTopPane() {
        setLabelFlagsLeft();
        initChoiceBoxes();
    }

    /**
     * A setter method sets the view of labelTimer to match the current time elapsed of the
     * game in progress
     */
    public void setLabelTimer() {
        Platform.runLater(() -> this.labelTimer.setText("" + theModel.getGameTimer().getElapsedTime()));
    }

    /**
     * A getter method for the label timer
     *
     * @return the current label for the timer
     */
    public Label getLabelTimer() {
        return labelTimer;
    }

    /**
     * A getter method for the quit button
     *
     * @return the instance of the quit Button
     */
    public Button getButtonQuit() {
        return buttonQuit;
    }

    /**
     * A getter method for the info button
     *
     * @return the instance of the info button
     */
    public Button getButtonInfo() {
        return buttonInfo;
    }

    /**
     * A setter method that sets the flags remaining label to the number of flags remaining
     */
    public void setLabelFlagsLeft() {
        this.labelFlagsLeft.setText("" + theModel.flagsRemaining());
    }

    public ChoiceBox<String> getChoiceColorMode() {
        return choiceColorMode;
    }

    public ChoiceBox<String> getChoiceChallengeLevel() {
        return choiceChallengeLevel;
    }

    public String getCurrentChallengeLevel() {
        return currentChallengeLevel;
    }

    public void setCurrentChallengeLevel(String currentChallengeLevel) {
        this.currentChallengeLevel = currentChallengeLevel;
    }

    /**
     * Called to initialize a controller after its root element has been completely processed
     */
    @FXML
    void initialize() {
//        assert root != null : "fx:id=\"root\" was not injected: check your FXML file 'minesweepermvc.fxml'.";
//        assert topPane != null : "fx:id=\"topPane\" was not injected: check your FXML file 'minesweepermvc.fxml'.";

        assert root != null : "fx:id=\"root\" was not injected: check your FXML file 'minesweepermvc.fxml'.";
        assert topPane != null : "fx:id=\"topPane\" was not injected: check your FXML file 'minesweepermvc.fxml'.";

        assert buttonInfo != null : "fx:id=\"buttonInfo\" was not injected: check your FXML file 'minesweepermvc.fxml'.";
        assert buttonQuit != null : "fx:id=\"buttonQuit\" was not injected: check your FXML file 'minesweepermvc.fxml'.";
        assert choiceChallengeLevel != null : "fx:id=\"choiceChallengeLevel\" was not injected: check your FXML file 'minesweepermvc.fxml'.";
        assert choiceColorMode != null : "fx:id=\"choiceColorMode\" was not injected: check your FXML file 'minesweepermvc.fxml'.";
        assert imageFlag != null : "fx:id=\"imageFlag\" was not injected: check your FXML file 'minesweepermvc.fxml'.";
        assert imageTimer != null : "fx:id=\"imageTimer\" was not injected: check your FXML file 'minesweepermvc.fxml'.";
        assert labelFlagsLeft != null : "fx:id=\"labelFlagsLeft\" was not injected: check your FXML file 'minesweepermvc.fxml'.";
        assert labelTimer != null : "fx:id=\"labelTimer\" was not injected: check your FXML file 'minesweepermvc.fxml'.";
        assert labelTitle != null : "fx:id=\"labelTitle\" was not injected: check your FXML file 'minesweepermvc.fxml'.";

    }

    /**
     * Sets up the choice boxes with the different options
     * for color modes and challenge levels
     */
    public void initChoiceBoxes(){
        //fills the choice box with the challenge levels
        choiceChallengeLevel.getItems().add("Easy");
        choiceChallengeLevel.getItems().add("Regular");
        choiceChallengeLevel.getItems().add("Hard");
        //sets the initial value of the box to regular
        choiceChallengeLevel.setValue("Regular");

        //fills the choice box with the different color modes
        choiceColorMode.getItems().add("Natural Mode");
        choiceColorMode.getItems().add("Pink Mode");
        choiceColorMode.getItems().add("Grayscale Mode");
        //sets the initial value of the box to natural
        choiceColorMode.setValue("Natural Mode");
    }

    /**
     * Set up the board pane, which contains all the cells
     */
    public void initBoardPane() {
        cells = new Rectangle[theModel.getRowNumber()][theModel.getColumnNumber()];
        cellContainers = new StackPane[theModel.getRowNumber()][theModel.getColumnNumber()];
        // Set the size of each square
//        double size = 40;
        double width = BOARD_WIDTH / theModel.getColumnNumber();
        double height = BOARD_HEIGHT / theModel.getRowNumber();
        for (int i = 0; i < theModel.getRowNumber(); i++) {
            for (int j = 0; j < theModel.getColumnNumber(); j++) {
                Cell cellModel = theModel.getBoard()[i][j];
                Rectangle cellView = new Rectangle(i * width, j * height, width, height);
                // Set the color of each cell based on its position
                if ((i + j) % 2 == 0) {
                    cellModel.setCurrentColor(cellModel.getColorMode().getLIGHT_UNPOENED());
                    cellModel.setOriginalColor(cellModel.getColorMode().getLIGHT_UNPOENED());
                }
                else {
                    cellModel.setCurrentColor(cellModel.getColorMode().getDARK_UNOPENED());
                    cellModel.setOriginalColor(cellModel.getColorMode().getDARK_UNOPENED());
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
