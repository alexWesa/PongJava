package no.uib.inf101.pong.view;

import no.uib.inf101.grid.GridCell;
import no.uib.inf101.grid.GridDimension;
import no.uib.inf101.pong.model.GameState;

public interface ViewablePongModel {
    /**
     * The dimensions of the board i.e. number of rows and columns
     *
     * @return an object of the type GridDimension
     */
    GridDimension getDimension();

    /**
     * An object that when iterated over returns all positions
     * and corresponding values
     *
     * @return an iterable object
     */
    Iterable<GridCell> getTilesOnBoard();

    /**
     * An object that when iterated over returns all positions
     * and corresponding values of paddles on board
     * @return an iterable object
     */
    Iterable<GridCell> getPaddlesOnBoard();

    /**
     * An object that when iterated over returns all positions
     * and corresponding values of ball on board
     * @return an iterable object
     */
    Iterable<GridCell> getBallOnBoard();

    /**
     * The current score of the game, e.g.  1 - 4
     * @return the current score as a String
     */
    String getScore();

    /**
     *
     * @return current state of the game
     */
    GameState getGameState();
}
