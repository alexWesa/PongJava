package no.uib.inf101.pong.controller;

import no.uib.inf101.pong.model.GameState;

public interface ControllablePongModel {

    /**
     * Moves the left paddle
     * @param deltaRow how much the paddle is moved by
     * @return true if legal move, false otherwise
     */
    boolean moveLeftPaddle(int deltaRow);

    /**
     * Moves the right paddle
     * @param deltaRow how much the paddle is moved by
     * @return true if legal move, false otherwise
     */
    boolean moveRightPaddle(int deltaRow);

    /**
     *
     * @return current state of the game
     */
    GameState getGameState();

    /**
     *
     * @return ms between each clock tick
     */
    int getTimeInterval();

    /**
     * Performs one clock tick in game
     */
    void clockTick();

}


