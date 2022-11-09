package minesweepermvc;/* *****************************************
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
 * Description: A class that creates and holds the values for individual cells.
 * They each hold the location on the board, the value or whether it's a bomb,
 * and how the user has interacted with it.
 *
 * ****************************************
 */

import javafx.beans.property.SimpleObjectProperty;
import javafx.scene.image.Image;
import javafx.scene.paint.Color;

/**
 * A class that creates and holds the values for individual cells.
 * They each hold the location on the board, the value or whether it's a bomb,
 * and how the user has interacted with it.
 */
public class Cell {
    private String value;
    private boolean isBomb;
    private boolean isOpen;
    private boolean isFlag;
    // Image of the cell (eg: number 1, 2, 3, 4, flag, bomb)
    private Image image;
    private SimpleObjectProperty<Color> currentColor;
    public final Color lightGreen = Color.web("#9CD375");
    public final Color darkGreen = Color.web("#668A4D");
    public final Color lightBrown = Color.web("#D1BA50");
    public final Color darkBrown = Color.web("#9B7D0A");

    public Cell(){
        this.isBomb = false;
        this.isOpen = false;
        this.isFlag = false;
        this.image = null;
        this.currentColor = new SimpleObjectProperty<>();
    }

    public boolean isBomb() {
        return isBomb;
    }

    public void setBomb(boolean bomb) {
        isBomb = bomb;
    }

    public boolean isOpen() {
        return isOpen;
    }

    public void setOpen(boolean open) {
        isOpen = open;
    }

    public boolean isFlag() {
        return isFlag;
    }

    public void setFlag(boolean flag) {
        isFlag = flag;
    }

    public Image getImage() {
        return image;
    }

    public void setImage(Image image) {
        this.image = image;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public Color getCurrentColor() {
        return currentColor.get();
    }

    public SimpleObjectProperty<Color> currentColorProperty() {
        return currentColor;
    }

    public void setCurrentColor(Color currentColor) {
        this.currentColor.set(currentColor);
    }

    // Change color and state of a cell when it is open
    public void openCell() {
        isOpen = true;
        if (!isBomb) {
            if (getCurrentColor() == lightGreen) {
                this.currentColor.set(lightBrown);
            } else {
                this.currentColor.set(darkBrown);
            }
        }
    }
}