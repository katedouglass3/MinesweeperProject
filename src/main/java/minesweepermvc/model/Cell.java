package minesweepermvc.model;/* *****************************************
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

import javafx.scene.image.Image;

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
    private double size;   // depends on total number of cells of board

    public Cell(){
        this.isBomb = false;
        this.isOpen = false;
        this.isFlag = false;
        this.image = null;
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

    public void setSize(double size) {
        this.size = size;
    }

    public void click() {

    }
}