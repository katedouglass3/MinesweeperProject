package minesweepermvc;

import javafx.scene.paint.Color;

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
    private final Color lightUnopened;
    private final Color darkUnopened;
    private final Color lightOpened;
    private final Color darkOpened;

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
        this.lightUnopened = lightUnopened;
        this.darkUnopened = darkUnopened;
        this.lightOpened = lightOpened;
        this.darkOpened = darkOpened;
    }

    public Color getLightUnopened() {
        return lightUnopened;
    }

    public Color getDarkUnopened() {
        return darkUnopened;
    }

    public Color getLightOpened() {
        return lightOpened;
    }

    public Color getDarkOpened() {
        return darkOpened;
    }
}
