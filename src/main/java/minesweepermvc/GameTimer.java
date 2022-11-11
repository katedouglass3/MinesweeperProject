/* *****************************************
 * CSCI205 - Software Engineering and Design
 * Fall 2022
 * Instructor: Prof. Brian King
 *
 * Name: Team 4
 * Date: 11/9/2022
 * Time: 10:54 PM
 *
 * Project: csci205_final_project
 * Package: minesweepermvc
 * Class: GameTimer
 * Description: This class controls the timing mechanism of the
 * minesweeper app. It is not dependent on any other classes.
 * The class starts and ends a timer and stores the current time
 * and the best time.
 *
 * ****************************************
 */

package minesweepermvc;

/**
 * This class controls the timing mechanism of the minesweeper app.
 * The class starts and ends a timer and stores the current time
 * and the best time.
 */
public class GameTimer {
    /** A conversion factor for nanoseconds to seconds */
    public static final int NANOS_TO_SECONDS = 1000000000;

    /** An int representing the best time in seconds */
    private int bestTime;

    /** A long representing the most recent completion time in seconds */
    private long currentTime;

    /** A long representing the start time in seconds */
    private long startTime;

    /**
     * A constructor that initializes bestTime and currentTime to 0.
     * This will be called when the app is started.
     */
    public GameTimer() {
        this.bestTime = 0;
        this.currentTime = 0;
    }

    /**
     * A method that starts the timer.
     * This will be called when a cell is first clicked.
     */
    public void startTimer() {
        // Start our timer
        startTime = System.nanoTime();
    }

    /**
     * A method that stops timing and updates currentTime and bestTime as needed.
     * currentTime updates if the game was won.
     * bestTime updates if the new currentTime is faster than the bestTime.
     * This method is called when the game is won or lost.
     *
     * @param isGameWon - a boolean representing whether the game has been won
     */
    public void endTimer(boolean isGameWon) {
        // If the game was won, set the current time to the elapsed time
        if (isGameWon) {
            currentTime = getElapsedTime();
            // If the new current time is faster than the best time (or bestTime was 0)
            // Set the best time to the new current time
            if (bestTime == 0 || currentTime < bestTime) {
                bestTime = (int) currentTime;
            }
        }
        // Otherwise, the game is lost, set current time to 0
        else {
            currentTime = 0;
        }
    }

    /**
     * A getter method for bestTime
     * @return bestTime - an int holding the best time in seconds
     */
    public int getBestTime() {
        return bestTime;
    }

    /**
     * A getter method for the elapsed time
     * @return the time between the actual time and the start time
     */
    public int getElapsedTime() {
        // Calculate the elapsed time (the time between the actual time and the start time) in nanoSeconds
        long elapsedNanos = System.nanoTime() - startTime;
        // Return the elapsed time converted to seconds
        return (int) (elapsedNanos / NANOS_TO_SECONDS);
    }

}
