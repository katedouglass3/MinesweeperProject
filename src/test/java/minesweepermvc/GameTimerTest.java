package minesweepermvc;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.concurrent.TimeUnit;

import static org.junit.jupiter.api.Assertions.*;

/**
 * A test class for the gameTimer. startTimer and endTimer are tested within the
 * other test methods.
 */
class GameTimerTest {
    /** An instance of the GameTimer class */
    GameTimer gameTimer;

    /**
     * Before each test, set gameTimer to a new instance of GameTimer
     */
    @BeforeEach
    void setUp() {
        gameTimer = new GameTimer();
    }

    @Test
    void getBestTime() throws InterruptedException {
        // Test that the original best time is 0
        assertEquals(0, gameTimer.getBestTime());

        // Start the timer
        gameTimer.startTimer();

        // Wait 10 seconds
        TimeUnit.SECONDS.sleep(10);

        // End the timer with won game
        gameTimer.endTimer(true);

        // Test that the best time is now 10
        assertEquals(10, gameTimer.getBestTime());

        // Start a new timer
        gameTimer.startTimer();

        // Wait 15 seconds
        TimeUnit.SECONDS.sleep(15);

        // End the timer with won game
        gameTimer.endTimer(true);

        // Test that the best time is still 10
        assertEquals(10, gameTimer.getBestTime());

        // Start a new timer
        gameTimer.startTimer();

        // Wait 5 seconds
        TimeUnit.SECONDS.sleep(5);

        // End the timer with won game
        gameTimer.endTimer(true);

        // Test that the best time is now 5
        assertEquals(5, gameTimer.getBestTime());

        // Start a new timer
        gameTimer.startTimer();

        // Wait 3 seconds
        TimeUnit.SECONDS.sleep(15);

        // End the timer with won lost
        gameTimer.endTimer(false);

        // Test that the best time is still 5
        assertEquals(5, gameTimer.getBestTime());
    }

    @Test
    void getElapsedTime() throws InterruptedException {
        // Start the timer
        gameTimer.startTimer();

        // Wait 10 seconds
        TimeUnit.SECONDS.sleep(10);

        // Test that the elapsed time is now 10
        assertEquals(10, gameTimer.getElapsedTime());

        // Wait 5 more seconds
        TimeUnit.SECONDS.sleep(5);

        // Test that the elapsed time is now 15
        assertEquals(15, gameTimer.getElapsedTime());

        // End the timer
        gameTimer.endTimer(true);
    }
}