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

import java.util.ArrayList;
import java.util.Random;

/**
 * This is the "model" for our Minesweeper app. It creates
 * a board of a certain size that holds cells. It also creates the different
 * interface options available to the user.
 */
public class MinesweeperModel {
    /** An instance of the game timer */
    private final GameTimer gameTimer;

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

    /**
     * A getter method that returns the double array representing the board of Cells
     *
     * @return - the double array representing the board of Cells
     */
    public Cell[][] getBoard() {
        return board;
    }

    /**
     * A getter method that returns the number of rows in the board
     *
     * @return - the int for the number of rows in the board
     */
    public int getRowNumber() {
        return rowNumber;
    }

    /**
     * A getter method that returns the number of columns in the board
     *
     * @return - the int for the number of columns in the board
     */
    public int getColumnNumber() {
        return columnNumber;
    }

    /**
     * A getter method that returns the number of bombs in the board
     *
     * @return - the int for the number of bombs in the board
     */
    public int getBombNumber() {
        return bombNumber;
    }

    /**
     * A getter method that returns the state that the game is in
     *
     * @return - the enum for the game state
     */
    public GameState getState() {
        return state;
    }

    /**
     * A setter method for state that sets the game state to the given state
     *
     * @param gameState the game state for the state to be set to
     */
    public void setState(GameState gameState) {
        state = gameState;
    }

    /**
     * Initializes our model
     *
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
        this.gameTimer = new GameTimer();
        generateBlankBoard();
    }

    /**
     * Creates an empty board of our specified dimensions.
     */
    private void generateBlankBoard() {
        for (int i = 0; i < rowNumber; i++) {
            for (int j = 0; j < columnNumber; j++) {
                board[i][j] = new Cell(i, j);
            }
        }
    }

    /**
     * Sets the specified number of bombs for our game in random
     * positions on the board.
     */
    private void generateBombAtRandomPosition() {
        Random rand = new Random();
        for (int i = 0; i < bombNumber; i++) {
            int rowPosition, columnPosition;
            do {
                // Generate a random row position
                rowPosition = rand.nextInt(rowNumber);
                // Generate a random column position
                columnPosition = rand.nextInt(columnNumber);
            }
            while (board[rowPosition][columnPosition].getHiddenValue() != null);
            // Create a cell at previous coordinates and set it to be a bomb
            board[rowPosition][columnPosition].setHiddenValue("*");  // "*" represents a bomb
            board[rowPosition][columnPosition].setBomb(true);
        }
    }

    /**
     * For cells that are not a bomb, we look at all the cells adjacent
     * to it and count how many of them are bombs. Set that number to
     * be the value of the cell.
     */
    public void fillRemainingCells() {
        // For each cell in the board
        for (int i = 0; i < rowNumber; i++) {
            for (int j = 0; j < columnNumber; j++) {
                // We only find value of cells that are not a bomb
                if (!board[i][j].isBomb()) {
                    int numberOfBombNeighbors = 0;
                    int[] range = rangeOfNeighbors(i, j);
                    // For each of the neighbors, check if it is a bomb
                    for (int x = range[0]; x <= range[1]; x++) {
                        for (int y = range[2]; y <= range[3]; y++) {
                            // If a neighbor is a bomb, add to the numberOfBombNeighbors
                            if (board[x][y].isBomb())   numberOfBombNeighbors++;
                        }
                    }
                    // Set the number display value to be the numberOfBombNeighbors
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
     * Prints the value of each cell to the console in a board arrangement
     */
    public void displayBoard() {
        // For each cell in the board
        for (int i = 0; i < rowNumber; i++) {
            for (int j = 0; j < columnNumber; j++) {
                // Print the hidden value (number or bomb) to console
                System.out.printf("%2s ", board[i][j].getHiddenValue());
            }
            System.out.println();
        }
    }

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
        state = GameState.IN_PROGRESS;
        for (Cell[] row : board) {
            for (Cell cell : row) {
                // If a bomb is opened, the game has been lost
                if (cell.isOpen() && cell.isBomb()) {
                    state = GameState.GAME_LOST;
                }
                // If any non-bomb cell has not been opened, the game is not done
                if (!cell.isOpen() && !cell.isBomb()) {
                    allCellsOpened = false;
                }
                // TODO: do we still need this since it is set at the beginning?
                // If any cell is opened, this is not a new game anymore
                if (cell.isOpen() && state.equals(GameState.NEW_GAME)) {
                    state = GameState.IN_PROGRESS;
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
            for (int i = 0; i < numBombsToBeReadded; i++){
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

        // Display Board for testing
        System.out.println();
        System.out.println("bombs regenerated");
        displayBoard();
    }

    /**
     * A function so that when we click on a blank cell (i.e. no bombs around), the program will
     * automatically open other cells as far as possible until we meet a numbered cell or a bomb
     *
     * @param x - the x coordinate of the cell to extend around
     * @param y - the y coordinate of the cell to extend around
     */
    public void autoExtendCells(int x, int y) {
        int[][] adjacentDirections = {{-1, 0}, {0, -1}, {0, 1}, {1, 0}};
        int[][] allDirections = {{-1, -1}, {-1, 0}, {-1, 1}, {0, -1}, {0, 1}, {1, -1}, {1, 0}, {1, 1}};

        // Check the immediate cells around it for 0s
        for (int[] aroundDirection : allDirections) {
            int aroundX = x + aroundDirection[0];
            int aroundY = y + aroundDirection[1];
            if (isInsideBoard(aroundX, aroundY)) {
                Cell aroundCell = this.board[aroundX][aroundY];
                if (!aroundCell.isOpen() && !aroundCell.isBomb() &&
                        !aroundCell.getHiddenValue().equals("0")) {
                    aroundCell.leftClick();
                }
            }
        }

        // Check any adjacent cells that were 0 for neighboring 0s
        for (int[] adjacentDirection : adjacentDirections) {
            int adjacentX = x + adjacentDirection[0];
            int adjacentY = y + adjacentDirection[1];
            if (isInsideBoard(adjacentX, adjacentY)) {
                Cell adjacentCell = this.board[adjacentX][adjacentY];
                if (!adjacentCell.isOpen() && adjacentCell.getHiddenValue().equals("0")) {
                    adjacentCell.leftClick();
                    autoExtendCells(adjacentX, adjacentY);
                }
            }
        }
    }

    /**
     * Check if the coordinates are within the range of the board
     *
     * @param x - the x coordinate to be checked
     * @param y - the y coordinate to be checked
     * @return - a boolean representing whether the coordinate is in the board range
     */
    public boolean isInsideBoard(int x, int y) {
        return x >= 0 && x < this.rowNumber && y >= 0 && y < this.columnNumber;
    }

    /**
     * A method that resets the board to a new game for when the user wants to play again.
     */
    public void resetBoard() {
        // Set the board to a new board with the same dimensions
        this.board = new Cell[this.rowNumber][this.columnNumber];
        // Reset the openCellNumber to 0
        this.openCellNumber = 0;
        // Reset the state to New Game
        this.state = GameState.NEW_GAME;
        // Generate a new blank board
        generateBlankBoard();
//        // Generate bombs randomly
//        generateBombAtRandomPosition();
        // Create the complete model
        createCompleteModel();
    }

    /**
     * A getter method for gameTimer
     *
     * @return gameTimer the instance of the current timer
     */
    public GameTimer getGameTimer() {
        return gameTimer;
    }

    /**
     * A main method to set up our model
     *
     * @param args - the command line prompts
     */
    public static void main(String[] args) {
        MinesweeperModel model = new MinesweeperModel(6, 6, 8);
        model.generateBombAtRandomPosition();
        model.displayBoard();
    }
}