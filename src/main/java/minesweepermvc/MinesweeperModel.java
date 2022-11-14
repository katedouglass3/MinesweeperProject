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

package minesweepermvc;

import java.util.ArrayList;
import java.util.Random;

/**
 * This is the "model" for our Minesweeper app. It creates
 * a board of a certain size that holds cells. It also creates the different
 * interface options availale to the user.
 */
public class MinesweeperModel {

    /** The number of rows on our board */
    private int rowNumber;

    /** The number of columns on our board */
    private int columnNumber;

    /** The number of bombs on our board */
    private int bombNumber;

    /** A 2D array of Cell objects to represent our board */
    private Cell[][] board;

    /** Number of open cells */
    private int openCellNumber;

    /** The current state of the game */
    private GameState state;

    public Cell[][] getBoard() {
        return board;
    }

    public int getRowNumber() {
        return rowNumber;
    }

    public int getColumnNumber() {
        return columnNumber;
    }

    public GameState getState() {
        return state;
    }

    /**
     * Initializes our model
     * @param rowNumber - the number of rows in the board
     * @param columnNumber - the number of columns in the board
     * @param bombNumber - the number of bombs
     */
    public MinesweeperModel(int rowNumber, int columnNumber, int bombNumber) {
        this.rowNumber = rowNumber;
        this.columnNumber = columnNumber;
        // TODO: calculate bomb number based on board size so that it is not required as
        // a parameter.
        this.bombNumber = bombNumber;
        this.board = new Cell[this.rowNumber][this.columnNumber];
        this.openCellNumber = 0;
        this.state = GameState.NEW_GAME;
        generateBlankBoard();
    }

    /**
     * Creates an empty board of our specified dimensions.
     */
    private void generateBlankBoard() {
        for (int i = 0; i < rowNumber; i++) {
            for (int j = 0; j < columnNumber; j++) {
                board[i][j] = new Cell();
            }
        }
    }

    /**
     * Sets the specified number of bombs for our game in random
     * positions on the board.
     */
    private void generateBombAtRandomPosition() {
        Random rand = new Random();
        for (int i = 0; i <= bombNumber; i++) {
            // Generate a random row position
            int rowPosition = rand.nextInt(rowNumber);
            // Generate a random column position
            int columnPosition = rand.nextInt(columnNumber);
            // Create a Cell at previous coordinates and set it to be a bomb
            Cell cell = new Cell();
            cell.setHiddenValue("*");  // "*" represents a bomb
            cell.setBomb(true);
            board[rowPosition][columnPosition] = cell;
        }
    }

    /**
     * For cells that are not a bomb, we look at all the cells adjacent
     * to it and count how many of them are bombs. Set that number to
     * be the value of the cell.
     */
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
                    board[i][j].setHiddenValue(numberOfBombNeighbors + "");
                }

            }
        }
    }

    /**
     * This is a helper function for fillRemainingCells() and regenerateAdjacentBombs()
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

        if (rowPosition == rowNumber - 1) bottomMostPosition = rowPosition;
        else bottomMostPosition = rowPosition + 1;

        if (columnPosition == 0) leftMostPosition = 0;
        else leftMostPosition = columnPosition - 1;

        if (columnPosition == columnNumber - 1) rightMostPosition = columnPosition;
        else rightMostPosition = columnPosition + 1;

        return new int[]{upperMostPosition, bottomMostPosition, leftMostPosition, rightMostPosition};
    }

    /**
     * Prints the value of each cell to the screen in a board
     * arrangement
     */
    public void displayBoard() {
        for (int i = 0; i < rowNumber; i++) {
            for (int j = 0; j < columnNumber; j++) {
                System.out.printf("%2s ", board[i][j].getHiddenValue());
            }
            System.out.println();
        }
    }

    // TODO: A function so that when we click on a blank cell (i.e no bombs around), the program will
    // automatically open other cells as far as possible until we met a numbered cell or a bomb
    // (try the online game)

    /**
     * Generate a complete model after all bombs and numbered cells
     * are filled correctly
     */
    public void createCompleteModel() {
        this.generateBombAtRandomPosition();
        this.fillRemainingCells();
    }

    /**
     * This function will check the status of all the cells on
     * the board to see whether the game has been won or lost,
     * or is still in progress. Updates the game state accordingly.
     */
    public void checkIfGameOver() {
        boolean allCellsOpened = true;
        for (Cell[] row : board) {
            for (Cell cell : row) {
                // If a bomb is opened, the game has been lost
                if (cell.isOpen() && cell.isBomb()) {
                    state = GameState.GAME_LOST;
                }
                // If any non-bomb cell has not been opened, the game is not done
                else if (!cell.isOpen() && !cell.isBomb()) {
                    allCellsOpened = false;
                }
            }
        }
        if (allCellsOpened) {
            state = GameState.GAME_WON;
        }
    }

    /**
     * A method that computes the number of flags remaining to be displayed. Can be negative.
     *
     * @return an int representing the number of flags left to be placed
     */
    public int flagsRemaining() {
        int flagsPlaced = 0;
        // Check each cell if a flag is placed, and if so add to total flagsPlaced
        for (Cell[] row : board) {
            for (Cell cell : row) {
                if (cell.isFlag()){
                    flagsPlaced ++;
                }
            }
        }

        return (bombNumber - flagsPlaced);
    }

    /**
     * A method that regenerates any bombs on or adjacent to the initial cell clicked on.
     *
     * @param row - the row of the cell clicked on
     * @param column - the column of the cell clicked on
     */
    public void regenerateAdjacentBombs(int row, int column){
        ArrayList<Integer> rows = new ArrayList<>();
        ArrayList<Integer> columns = new ArrayList<>();
        int numBombsToBeReadded = 0;
        int[] adjacentRange = rangeOfNeighbors(row, column);  // returns an array with upperMost, bottomMost, leftMost, and rightMostPos

        // Go through all the adjacent cells and the clicked cell and count num of bombs in them
        for (int x = adjacentRange[0]; x <= adjacentRange[1]; x++) {
            for (int y = adjacentRange[2]; y <= adjacentRange[3]; y++) {
                // If the cell contains a bomb, add to number to be readded (remove it later so not readded to it)
                if (board[x][y].isBomb()){
                    numBombsToBeReadded ++;
                }
            }
        }

        // If there are bombs that need to be regenerated, do so
        if (numBombsToBeReadded > 0){
            // For each bomb to be readded
            for (int i = 0; i <= numBombsToBeReadded; i++){
                // Create a list of empty cells that bomb could be placed in
                ArrayList<Cell> emptyCells = new ArrayList<>();
                for (Cell[] cellRow : board) {
                    for (Cell cell : cellRow) {
                        if (!cell.isBomb()){
                            emptyCells.add(cell);
                        }
                    }
                }
                // Pick a random empty cell to add bomb to
                int rnd = new Random().nextInt(emptyCells.size());
                Cell cellToBeAdded = emptyCells.get(rnd);
                // Add bomb to empty cell
                cellToBeAdded.setBomb(true);
            }
        }

        // Remove bombs from clicked and adjacent cells
        for (int x = adjacentRange[0]; x <= adjacentRange[1]; x++) {
            for (int y = adjacentRange[2]; y <= adjacentRange[3]; y++) {
                // Remove bombs from these cells
                    board[x][y].setBomb(false);
            }
        }
    }

    /**
     * A main method to set up our model
     *
     * @param args args
     */
    public static void main(String[] args) {
        MinesweeperModel model = new MinesweeperModel(6, 6, 8);
        model.generateBombAtRandomPosition();
        // TODO: check if something should be here
        model.fillRemainingCells();
        model.displayBoard();

//        System.out.println(Arrays.toString(model.rangeOfNeighbors(0, 2)));   //0, 1, 1, 3
    }
}