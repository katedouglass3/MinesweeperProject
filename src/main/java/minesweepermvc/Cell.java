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
 * Class: Cell
 *
 * Description: A class to represent a single cell. They each hold whether they
 * are a bomb, opened, or flagged and their location on the board.
 *
 * ****************************************
 */

package minesweepermvc;

import javafx.beans.property.SimpleObjectProperty;
import javafx.scene.image.Image;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

/**
 * A class that creates and holds the values for individual cells. They each hold
 * the location on the board, the value or whether it's a bomb, and how the user
 * has interacted with it.
 */
public class Cell extends Rectangle {

    /** The value of the cell, not shown until the cell is clicked on */
    private String hiddenValue;

    /** A boolean value for if a cell contains a bomb or not */
    private boolean isBomb;

    /** A boolean value for if a cell is open (has been clicked on) or not */
    private boolean isOpen;

    /** A boolean value for is a cell is marked with a flag or not */
    private boolean isFlag;

    /** The row that a cell is located in */
    private int rowNumber;

    /** The column that a cell is located in */
    private int columnNumber;

    /** Flag image for the cell if isFlag is true */
    private final Image flagImage = new Image("redFlag.png");

    /** Bomb image for the cell if isFlag is true */
    private final Image bombImage = new Image("bomb.png");

    /** The color displayed in the cell */
    private SimpleObjectProperty<Color> currentColor;

    /** The text to be displayed in the cell */
    private SimpleObjectProperty<String> displayValue;

    /** The image to be displayed in the cell */
    private SimpleObjectProperty<Image> imageValue;

    /**
     * Colors to be used for the cell colors
     */
    public final Color lightGreen = Color.web("#9CD375");
    public final Color darkGreen = Color.web("#668A4D");
    public final Color lightBrown = Color.web("#D1BA50");
    public final Color darkBrown = Color.web("#9B7D0A");

    /** The model which contains this cell */
    private MinesweeperModel theModel;


    /**
     * Constructor for a Cell instance with row, column and model params
     *
     * @param row - the row that a cell is located in
     * @param col - the column that a cell is located in
     * @param theModel - the model that this cell belongs to
     */
    public Cell(int row, int col, MinesweeperModel theModel) {
        this.rowNumber = row;
        this.columnNumber = col;
        this.isBomb = false;
        this.isOpen = false;
        this.isFlag = false;
        this.currentColor = new SimpleObjectProperty<>();
        this.displayValue = new SimpleObjectProperty<>("");
        this.imageValue = new SimpleObjectProperty<>();
        this.theModel = theModel;
    }

    /**
     * Constructor for a Cell instance without params
     */
    public Cell() {
        this.isBomb = false;
        this.isOpen = false;
        this.isFlag = false;
        this.currentColor = new SimpleObjectProperty<>();
        this.displayValue = new SimpleObjectProperty<>("");
        this.imageValue = new SimpleObjectProperty<>();
    }

    /**
     * When a cell is clicked, it becomes open. If it is not a bomb, the current color is
     * set to dark green upon being opened. If it is a bomb, the color is set to dark brown.
     */
    public void leftClick() {
        if (!isOpen && !isFlag) {
            // Open the cell
            isOpen = true;
            // Change the color
            if (getCurrentColor() == lightGreen) {
                this.currentColor.set(lightBrown);
            } else {
                this.currentColor.set(darkBrown);
            }
            // Set the visual to be displayed (either a number or a bomb)
            if (!isBomb) {
                this.displayValue.setValue(this.hiddenValue);
                if (this.hiddenValue.equals("0")) {
                    theModel.autoExtendCells(this.rowNumber, this.columnNumber);
                }
            } else {
                this.imageValue.setValue(bombImage);
            }
        }
    }

    /**
     * When a cell is right-clicked, a flag is added or removed. If the cell has already
     * been opened, nothing happens.
     */
    public void rightClick() {
        if (!isOpen) {
            // If the cell is marked with a flag, remove the flag
            if (isFlag) {
                this.imageValue.setValue(null);
            }
            // If the cell is blank, add a flag
            else {
                this.imageValue.setValue(flagImage);
            }
            // Toggle whether of not the cell has a flag
            isFlag = !isFlag;
        }
    }

    /**
     * A getter method that returns whether a cell contains a bomb or not
     *
     * @return - a boolean for if a cell contains a bomb or not
     */
    public boolean isBomb() {
        return isBomb;
    }

    /**
     * A setter method that sets a cell to either have a bomb or not
     *
     * @param bomb - a boolean for if the cell should be set to have a bomb or not
     */
    public void setBomb(boolean bomb) {
        isBomb = bomb;
    }

    /**
     * A getter method that returns whether a cell has been opened (clicked on) or not
     *
     * @return - a boolean for if a cell has been opened or not
     */
    public boolean isOpen() {
        return isOpen;
    }

    /**
     * A setter method that sets a cell to either be open or not
     *
     * @param open - a boolean for if the cell should be set to be open or not
     */
    public void setOpen(boolean open) {
        isOpen = open;
    }

    /**
     * A getter method that returns whether a cell is marked with a flag or not
     *
     * @return - a boolean for if a cell is marked with a flag or not
     */
    public boolean isFlag() {
        return isFlag;
    }

    /**
     * A setter method that sets a cell to be marked with a flag or not
     *
     * @param flag - a boolean for if the cell should be marked with a flag or not
     */
    public void setFlag(boolean flag) {
        isFlag = flag;
    }

    /**
     * A getter method that returns the hidden value of the cell, not shown until the cell is clicked on
     *
     * @return - the hidden value of the cell
     */
    public String getHiddenValue() {
        return hiddenValue;
    }

    /**
     * A getter method that sets the hidden value of the cell, not shown until the cell is clicked on
     *
     * @param hiddenValue - the hidden value of the cell
     */
    public void setHiddenValue(String hiddenValue) {
        this.hiddenValue = hiddenValue;
    }

    /**
     * A getter method that returns the color displayed in the cell
     *
     * @return - the color displayed in the cell
     */
    public Color getCurrentColor() {
        return currentColor.get();
    }

    /**
     * A getter method that returns the SimpleObjectProperty<Color> of the cell
     *
     * @return - the SimpleObjectProperty<Color> of the cell
     */
    public SimpleObjectProperty<Color> currentColorProperty() {
        return currentColor;
    }

    /**
     * A setter method that sets the color to be displayed in the cell
     *
     * @param currentColor - the color displayed in the cell
     */
    public void setCurrentColor(Color currentColor) {
        this.currentColor.set(currentColor);
    }

    /**
     * A getter method that returns the text that is displayed in the cell
     *
     * @return - the text that is displayed in the cell
     */
    public String getDisplayValue() {
        return displayValue.get();
    }

    /**
     * A getter method that returns the text that is displayed in the cell
     *
     * @return - the text that is displayed in the cell
     */
    public SimpleObjectProperty<String> displayValueProperty() {
        return displayValue;
    }

    /**
     * A setter method that sets the text to be displayed in the cell
     *
     * @param displayValue - the text to be displayed in the cell
     */
    public void setDisplayValue(String displayValue) {
        this.displayValue.set(displayValue);
    }

    /**
     * A getter method that returns the image that is displayed in the cell
     *
     * @return - the image that is displayed in the cell
     */
    public Image getImageValue() {
        return imageValue.get();
    }

    /**
     * A getter method that returns the SimpleObjectProperty<Image> that is displayed in the cell
     *
     * @return - the SimpleObjectProperty<Image> that is displayed in the cell
     */
    public SimpleObjectProperty<Image> imageValueProperty() {
        return imageValue;
    }

    /**
     * A getter method that returns the row number that the cell is located in
     *
     * @return - the row the cell is located in
     */
    public int getCellRowNumber() {
        return rowNumber;
    }

    /**
     * A getter method that returns the column number that the cell is located in
     *
     * @return - the column the cell is located in
     */
    public int getCellColumnNumber() {
        return columnNumber;
    }

}