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
 * Description: This is the "model" for our Minesweeper app. It creates
 * a board of a certain size that holds cells. It also creates the different
 * interface options availale to the user.
 *
 * ****************************************
 */

package minesweepermvc.model;

import minesweepermvc.model.Cell;

import java.util.Arrays;
import java.util.Random;

/**
 * This is the "model" for our Minesweeper app. It creates
 * a board of a certain size that holds cells. It also creates the different
 * interface options availale to the user.
 */
public class MinesweeperModel {
    private int rowNumber;
    private int columnNumber;
    private int totalCellNumber;
    private int bombNumber;

    // A 2D array with each element being a Cell object
    private Cell[][] board;

    // Number of cells already open
    private int openCellNumber;

    public Cell[][] getBoard() {
        return board;
    }

    public int getRowNumber() {
        return rowNumber;
    }

    public int getColumnNumber() {
        return columnNumber;
    }

    // Create a new model with specific number of rows, columns and bombs
    public MinesweeperModel(int rowNumber, int columnNumber, int bombNumber) {
        this.rowNumber = rowNumber;
        this.columnNumber = columnNumber;
        this.bombNumber = bombNumber;
        this.totalCellNumber = this.rowNumber * this.columnNumber;
        this.board = new Cell[this.rowNumber][this.columnNumber];
        this.openCellNumber = 0;
        generateBlankBoard();
    }

    private void generateBlankBoard() {
        for (int i = 0; i < rowNumber; i++) {
            for (int j = 0; j < columnNumber; j++) {
                board[i][j] = new Cell();
            }
        }
    }

    // Generate bombs at random positions on the board
    private void generateBombAtRandomPosition() {
        Random rand = new Random();
        for (int i = 0; i <= bombNumber; i++) {
            // Generate a random row position
            int rowPosition = rand.nextInt(rowNumber);
            // Generate a random column position
            int columnPosition = rand.nextInt(columnNumber);
            // Create a Cell at previous coordinates and set it to be a bomb
            Cell cell = new Cell();
            cell.setValue("*");  // "*" represents a bomb
            cell.setBomb(true);
            board[rowPosition][columnPosition] = cell;
        }
    }

    // For cells that are not a bomb, we look at all the cells adjacent to it and count how many of them
    // are bombs. Set that number to be the value of the cell
    private void fillRemainingCells() {
        for (int i = 0; i < rowNumber; i++) {
            for (int j = 0; j < columnNumber; j++) {
                // We only find value of cells that are not a bomb
                if (!board[i][j].isBomb()) {
                    int numberOfBombNeighbors = 0;
                    int[] range = rangeOfNeighbors(i, j);
                    for (int x = range[0]; x <= range[1]; x++) {
                        for (int y = range[2]; y <= range[3]; y++) {
                            if (board[x][y].isBomb())   numberOfBombNeighbors++;
                        }
                    }
                    board[i][j].setValue(numberOfBombNeighbors + "");
                }

            }
        }
    }

    /**
     * This is a helper function for fillRemainingCells()
     *
     * This function looks at a cell and determine the upperMostPosition, bottomMostPosition,
     * leftMostPosition, rightMostPosition of its neighbors.
     * For example, let's examine the board below;
     *          a b c
     *          d e f
     *          g h i
     * If the cell we examine is e, then we return (0, 2, 0, 2) because its neighbors (a, b, c, d, f, g, h, i)
     * has row positions range from 0-2 and column positions range from 0-2
     * But if the cell we examine is b, then we return (0, 1, 0, 2) because its neighbor (a, c, d, e, f)
     * has row positions range from 0-1 and column positions range from 0-2
     *
     * @param rowPosition - row position of the examined cell
     * @param columnPosition - column position of the examined cell
     * @return an array with upperMostPosition, bottomMostPosition, leftMostPosition, rightMostPosition
     * of neighbors
     */
    private int[] rangeOfNeighbors(int rowPosition, int columnPosition) {
        int upperMostPosition, bottomMostPosition, leftMostPosition, rightMostPosition;

        if (rowPosition == 0) upperMostPosition = 0;
        else upperMostPosition = rowPosition - 1;

        if (rowPosition == columnNumber - 1) bottomMostPosition = rowPosition;
        else bottomMostPosition = rowPosition + 1;

        if (columnPosition == 0) leftMostPosition = 0;
        else leftMostPosition = columnPosition - 1;

        if (columnPosition == columnNumber - 1) rightMostPosition = columnPosition;
        else rightMostPosition = columnPosition + 1;

        return new int[]{upperMostPosition, bottomMostPosition, leftMostPosition, rightMostPosition};
    }

    private void displayBoard() {
        for (int i = 0; i < rowNumber; i++) {
            for (int j = 0; j < columnNumber; j++) {
                System.out.printf("%2s ", board[i][j].getValue());
            }
            System.out.println();
        }
    }

    // TODO: A function so that when we click on a blank cell (i.e no bombs around), the program will
    // automatically open other cells as far as possible until we met a numbered cell or a bomb
    // (try the online game)

    public static void main(String[] args) {
        MinesweeperModel model = new MinesweeperModel(6, 6, 8);
        model.generateBombAtRandomPosition();
        model.fillRemainingCells();
        model.displayBoard();

//        System.out.println(Arrays.toString(model.rangeOfNeighbors(0, 2)));   //0, 1, 1, 3
    }
}