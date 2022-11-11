package minesweepermvc;/* *****************************************
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
 * Description: The different game states for our minesweeper app.
 * These include NEW_GAME, IN_PROGRESS, WIN, and GAME_OVER.
 *
 * ****************************************
 */

/**
 * The different game states for our minesweeper app.
 * These include NEW_GAME, IN_PROGRESS, WIN, and GAME_OVER.
 */
public enum GameState {
    NEW_GAME,
    IN_PROGRESS,
    GAME_WON,
    GAME_LOST
}
