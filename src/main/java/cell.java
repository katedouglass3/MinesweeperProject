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
 * Description: A class that creates and holds the values for individual cells.
 * They each hold the location on the board, the value or whether it's a bomb,
 * and how the user has interacted with it.
 *
 * ****************************************
 */

/**
 * A class that creates and holds the values for individual cells.
 * They each hold the location on the board, the value or whether it's a bomb,
 * and how the user has interacted with it.
 */
public class cell {
    private int x;
    private int y;
    private int value;
    private boolean isBomb;
    private boolean isOpen;
    private boolean isFlag;

    public cell(int x, int y, int value){
        this.x = x;
        this.y = y;
        this.value = value;
        //TODO -- set the isBomb, isOpen, isFlag
    }
}