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
 * Class: MinesweeperModelTest
 *
 * Description: Initial tests for our MinesweeperModel class
 *
 * ****************************************
 */

package minesweepermvc;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Our original test class for the important methods of our initial model.
 * This class helped us check that the game state was updated correctly in
 * certain situations. After we further developed the Cell class, this test
 * file was no longer useful and didn't work with rendering Images.
 */
class MinesweeperModelTest {

    /** A model to use for all tests */
    private MinesweeperModel model;

    /**
     * Initializes a very simple model to use for all tests
     */
    @BeforeEach
    void setUp() {
        model = new MinesweeperModel(2, 2, 1);
        model.createCompleteModel();
    }

    /**
     * Tests that if all cells are clicked except for the bombs,
     * the game state will change to reflect a win.
     */
    @Test
    void checkIfGameOverForWin() {
        for (Cell[] row : model.getBoard()) {
            for (Cell cell : row) {
                if (!cell.isBomb()) {
                    cell.leftClick();
                }
            }
        }
        model.checkIfGameOver();
        assertEquals(GameState.GAME_WON, model.getState());
    }

    /**
     * Tests that if a bomb is opened, the game state will
     * change to reflect a loss.
     */
    @Test
    void checkIfGameOverForLoss() {
        for (Cell[] row : model.getBoard()) {
            for (Cell cell : row) {
                if (cell.isBomb()) {
                    cell.leftClick();
                }
            }
        }
        model.checkIfGameOver();
        assertEquals(GameState.GAME_LOST, model.getState());
    }
}