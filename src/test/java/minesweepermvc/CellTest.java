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
    private Cell cell1;

    /** A second cell to test on */
    private Cell cell2;

    /**
     * Initializes the cell to use before each test.
     */
    @BeforeEach
    void setUp() {
        cell1 = new Cell(0, 0);
        cell1.setColorMode(ColorMode.ORIGINAL);
        // Start as light green
        cell1.setOriginalColor(Color.web("#9CD375"));
        cell1.setHiddenValue("1");

        cell2 = new Cell(1, 0);
        cell2.setColorMode(ColorMode.ORIGINAL);
        // Start as dark green
        cell2.setOriginalColor(Color.web("#668A4D"));
        cell2.setHiddenValue("0");
    }

    /**
     * Makes sure that for a cell with no bomb, upon being clicked, the
     * color is changed to light brown for a light green cell, and dark
     * brown for a dark green cell, and that the display value is
     * updated accordingly, and the cell is marked as open.
     */
    @Test
    void leftClick() {
        assertFalse(cell1.isOpen());
        cell1.leftClick();
        // Since the cell started as light green, it should now be light brown
        assertEquals(Color.web("#D1BA50"), cell1.getCurrentColor());
        assertTrue(cell1.isOpen());
        assertEquals(cell1.getDisplayValue(), "1");

        cell2.leftClick();
        assertTrue(cell2.isOpen());
        // Dark green opens to dark brown
        assertEquals(Color.web("#9B7D0A"), cell2.getCurrentColor());
        // Since the hidden value is 0, there should be no text displayed
        assertEquals(cell2.getDisplayValue(), "");
    }

    /**
     * Makes sure that a flag cannot be added on a cell that has already
     * been opened.
     */
    @Test
    void rightClickOnOpen() {
        cell1.setOpen(true);
        cell1.rightClick();
        assertFalse(cell1.isFlag());
    }
}