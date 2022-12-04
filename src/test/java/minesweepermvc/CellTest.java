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
 * Class: CellTest
 *
 * Description: Initial test class for our Cell class.
 *
 * ****************************************
 */

package minesweepermvc;

import javafx.scene.paint.Color;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Our original test class for the important methods of our initial Cell class.
 * This class helped us check that our leftClick and rightClick methods in Cell
 * were correctly updating the cell color and boolean values. After we further
 * developed the cell class, this test file was no longer useful and didn't work
 * with rendering Images.
 */
class CellTest {

    /** A cell object to use for all tests. */
    private Cell cell;

    /**
     * Initializes the cell to use before each tests.
     */
    @BeforeEach
    void setUp() {
        cell = new Cell(0, 0);
    }

    /**
     * Makes sure that for a cell with no bomb, upon being clicked, the
     * color is changed to dark green and the cell is marked as open.
     */
    @Test
    void click() {
        assertFalse(cell.isOpen());
        cell.leftClick();
        assertEquals(Color.web("#668A4D"), cell.getCurrentColor());
        assertTrue(cell.isOpen());
    }

    /**
     * Makes sure that for a cell with a bomb, upon being clicked, the
     * color is set to dark brown and the cell is marked as open.
     */
    @Test
    void clickForBomb() {
        cell.setBomb(true);
        cell.leftClick();
        assertEquals(Color.web("#9B7D0A"), cell.getCurrentColor());
        assertTrue(cell.isOpen());
    }

    /**
     * Makes sure that for a cell that hasn't been opened yet, right-clicking
     * turns the cell red, and the cell is marked as having a flag.
     */
    @Test
    void rightClick() {
        cell.rightClick();
        assertTrue(cell.isFlag());
        assertEquals(Color.RED, cell.getCurrentColor());
    }

    /**
     * Makes sure that a flag cannot be added on a cell that has already
     * been opened.
     */
    @Test
    void rightClickOnOpen() {
        cell.setOpen(true);
        cell.rightClick();
        assertFalse(cell.isFlag());
        assertNotEquals(Color.RED, cell.getCurrentColor());
    }
}