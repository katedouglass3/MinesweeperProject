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
 * Description: A class to represent a single cell. They each hold their
 * value and whether they are a bomb, opened, or flagged.
 *
 * ****************************************
 */

package minesweepermvc;

import javafx.beans.property.SimpleObjectProperty;
import javafx.scene.image.Image;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

/**
 * A class that creates and holds the values for individual cells.
 * They each hold the location on the board, the value or whether it's a bomb,
 * and how the user has interacted with it.
 */
public class Cell extends Rectangle {
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
    public final Color red = Color.RED;

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

    /**
     * When a cell is clicked, it becomes open. If it is not a bomb,
     * the current color is set to dark green upon being opened. If it
     * is a bomb, the color is set to dark brown.
     */
    public void click() {
        isOpen = true;
        if (!isBomb) {
            this.currentColor.set(darkGreen);
        }
        else {
            this.currentColor.set(darkBrown);
        }
    }

    /**
     * When a cell is right-clicked, a flag is added or removed.
     * We will represent a flag being added by changing the color
     * of the cell to red. If a flag is being removed, the color is
     * changed back to light green. If the cell has already been
     * opened, nothing happens.
     */
    public void rightClick() {
        if (!isOpen) {
            if (isFlag) {
                isFlag = false;
                this.currentColor.set(lightGreen);
            } else {
                isFlag = true;
                this.currentColor.set(red);
            }
        }
    }
}