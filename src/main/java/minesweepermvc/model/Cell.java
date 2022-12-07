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

package minesweepermvc.model;

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

    /**
     * The value of the cell, not shown until the cell is clicked on
     */
    private String hiddenValue;

    /**
     * A boolean value for if a cell contains a bomb or not
     */
    private boolean isBomb;

    /**
     * A boolean value for if a cell is open (has been clicked on) or not
     */
    private boolean isOpen;

    /**
     * A boolean value for is a cell is marked with a flag or not
     */
    private boolean isFlag;

    /**
     * The row that a cell is located in
     */
    private int rowNumber;

    /**
     * The column that a cell is located in
     */
    private int columnNumber;

    /**
     * Flag image for the cell if isFlag is true
     */
    private Image flagImage;

    /**
     * Bomb image for the cell if isFlag is true
     */
    private Image bombImage;

    /**
     * The color currently displayed in the cell
     */
    private SimpleObjectProperty<Color> currentColor;

    /**
     * The original color of the cell
     */
    private SimpleObjectProperty<Color> originalColor;

    /**
     * The text to be displayed in the cell
     */
    private SimpleObjectProperty<String> displayValue;

    /**
     * The image to be displayed in the cell
     */
    private SimpleObjectProperty<Image> imageValue;

    /**
     * The colorMode selected by the user
     */
    private SimpleObjectProperty<ColorMode> colorMode;

    /**
     * Constructor for a Cell instance with row and column params - for testing
     *
     * @param row - the row that a cell is located in
     * @param col - the column that a cell is located in
     */
    public Cell(int row, int col) {
        this.rowNumber = row;
        this.columnNumber = col;
        this.isBomb = false;
        this.isOpen = false;
        this.isFlag = false;
        this.currentColor = new SimpleObjectProperty<>();
        this.originalColor = new SimpleObjectProperty<>();
        this.displayValue = new SimpleObjectProperty<>("");
        this.imageValue = new SimpleObjectProperty<>();
        this.colorMode = new SimpleObjectProperty<>(ColorMode.ORIGINAL);
    }

    /**
     * When a cell is clicked, it becomes open. If it is not a bomb, the current color is
     * updated to its opened color according to the current color mode. If it is a bomb,
     * the bomb image appears.
     */
    public void leftClick() {
        if (!isOpen && !isFlag) {
            // Open the cell
            isOpen = true;
            // Change the color
            if (getOriginalColor().equals(colorMode.getValue().getLIGHT_UNOPENED())) {
                this.currentColor.set(colorMode.getValue().getLIGHT_OPENED());
            } else {
                this.currentColor.set(colorMode.getValue().getDARK_OPENED());
            }
            // Set the visual to be displayed (either a number or a bomb)
            if (!isBomb) {
                if (!getHiddenValue().equals("0")) {
                    this.displayValue.setValue(this.hiddenValue);
                }
            } else {
                bombImage = new Image("bomb.png");
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
                flagImage = new Image("redFlag.png");
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
     * A getter method that returns the original color of the cell
     *
     * @return - the Color that the cell was originally set to
     */
    public Color getOriginalColor() {
        return originalColor.get();
    }

    /**
     * A setter method that sets the original color of the cell to the correct original color
     * based on the theme
     *
     * @param originalColor - the correct original color based on the theme
     */
    public void setOriginalColor(Color originalColor) {
        this.originalColor.set(originalColor);
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


    /**
     * A getter method that returns the colorMode property selected
     *
     * @return - the current colorModeProperty
     */
    public SimpleObjectProperty<ColorMode> colorModeProperty() {
        return colorMode;
    }

    /**
     * A getter method that returns the colorMode selected
     *
     * @return - the colorMode selected for the theme by the user
     */
    public ColorMode getColorMode() {
        return colorMode.getValue();
    }

    /**
     * A setter method that sets the colorMode theme
     *
     * @param colorMode - the color theme that the user selects
     */
    public void setColorMode(ColorMode colorMode) {
        this.colorMode.set(colorMode);
    }

}