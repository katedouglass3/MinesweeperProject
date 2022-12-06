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
 * Class: ColorMode
 *
 * Description: The different color modes for our minesweeper app.
 * These include ORIGINAL, PINK, and GRAYSCALE.
 *
 * ****************************************
 */

package minesweepermvc.model;

import javafx.scene.paint.Color;

/**
 * An enumeration class that encapsulates the different
 * possible color schemes for the cells in our minesweeper game.
 * Each color scheme includes four colors.
 */
public enum ColorMode {

    /**
     * Original uses colors light green, dark green, light brown, and dark brown
     */
    ORIGINAL(Color.web("#9CD375"), Color.web("#668A4D"), Color.web("#D1BA50"), Color.web("#9B7D0A")),

    /**
     * Pink uses colors light pink, pink, light yellow, and a golden yellow
     */
    PINK(Color.LIGHTPINK, Color.PINK, Color.web("#FAFAD2"), Color.web("#FFFFE0")),

    /**
     * Grayscale uses dark gray, black, off-white, and light gray
     */
    GRAYSCALE(Color.web("#A9A9A9").darker().darker(), Color.web("#000000").brighter(), Color.web("#F8F8FF"), Color.web("#DCDCDC"));

    /**
     * The four different colors needed for each color scheme
     */
    private final Color LIGHT_UNOPENED;
    private final Color DARK_UNOPENED;
    private final Color LIGHT_OPENED;
    private final Color DARK_OPENED;

    /**
     * Constructs a new color mode with four colors
     * @param lightUnopened - the color of the lighter cells in the checkerboard
     *                      pattern that haven't been opened yet
     * @param darkUnopened - the color of the darker cells in the checkerboard
     *                     pattern that haven't been opened yet
     * @param lightOpened - the color of the lighter cells after being opened
     * @param darkOpened - the color of the darker cells after being opened
     */
    ColorMode(Color lightUnopened, Color darkUnopened, Color lightOpened, Color darkOpened) {
        this.LIGHT_UNOPENED = lightUnopened;
        this.DARK_UNOPENED = darkUnopened;
        this.LIGHT_OPENED = lightOpened;
        this.DARK_OPENED = darkOpened;
    }

    /**
     * A getter method that returns the dark unopened color of the cell
     *
     * @return the dark unopened color of the cell
     */
    public Color getDARK_UNOPENED() {
        return DARK_UNOPENED;
    }

    /**
     * A getter method that returns the light opened color of the cell
     *
     * @return the light opened color of the cell
     */
    public Color getLIGHT_OPENED() {
        return LIGHT_OPENED;
    }

    /**
     * A getter method that returns the dark opened color of the cell
     *
     * @return the dark opened color of the cell
     */
    public Color getDARK_OPENED() {
        return DARK_OPENED;
    }

    /**
     * A getter method that returns the light unopened color of the cell
     *
     * @return the light unopened color of the cell
     */
    public Color getLIGHT_UNOPENED() {return LIGHT_UNOPENED;}
}
