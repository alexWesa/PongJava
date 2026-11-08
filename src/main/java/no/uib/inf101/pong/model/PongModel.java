package no.uib.inf101.pong.model;

import no.uib.inf101.grid.CellPosition;
import no.uib.inf101.grid.GridCell;
import no.uib.inf101.grid.GridDimension;
import no.uib.inf101.pong.controller.ControllablePongModel;
import no.uib.inf101.pong.model.ball.BallFactory;
import no.uib.inf101.pong.model.ball.PongBall;
import no.uib.inf101.pong.model.ball.PongBallFactory;
import no.uib.inf101.pong.model.paddle.PaddleFactory;
import no.uib.inf101.pong.model.paddle.PatternedPaddleFactory;
import no.uib.inf101.pong.model.paddle.PongPaddle;
import no.uib.inf101.pong.view.ViewablePongModel;

import java.util.ArrayList;
import java.util.Random;

public class PongModel implements ViewablePongModel, ControllablePongModel {

    private PongBoard board;
    private PongPaddle paddleLeft;
    private PongPaddle paddleRight;
    private PongBall pongBall;
    private BallFactory ballFactory;
    private GameState gameState;

    private int deltaX = 1;
    private int deltaY = 1;
    private int player1Score = 0;
    private int player2Score = 0;

    public PongModel() {
        this(new PongBoard(15, 25), new PatternedPaddleFactory(), new PongBallFactory());
    }

    public PongModel(PongBoard board, PaddleFactory paddleFactory, BallFactory ballFactory) {
        this.board = board;
        this.ballFactory = ballFactory;
        this.paddleLeft = paddleFactory.getNewPaddle().shiftedToLeftCenterOf(board);
        this.paddleRight = paddleFactory.getNewPaddle().shiftedToRightCenterOf(board);
        this.pongBall = ballFactory.getNewBall().shiftedToCenterOfBoard(board);
        this.gameState = GameState.ACTIVE_GAME;
    }

    @Override
    public GridDimension getDimension() {
        return board;
    }

    @Override
    public Iterable<GridCell> getTilesOnBoard() {
        return board;
    }

    @Override
    public Iterable<GridCell> getPaddlesOnBoard() {
        ArrayList<GridCell> paddles = new ArrayList<>();
        for (GridCell cL : paddleLeft) {
            paddles.add(cL);
            for (GridCell cR : paddleRight) {
                paddles.add(cR);
            }
        }
        return paddles;
    }

    @Override
    public Iterable<GridCell> getBallOnBoard() {
        return pongBall;
    }

    @Override
    public String getScore() {
        String score1 = Integer.toString(player1Score);
        String score2 = Integer.toString(player2Score);

        return score1 + " - " + score2;
    }

    @Override
    public GameState getGameState() {
        return gameState;
    }

    @Override
    public int getTimeInterval() {
        if (getGameState().equals(GameState.ACTIVE_GAME_FAST)) {
            return 1000 / 12;
        }
        return 1000 / 6;
    }

    @Override
    public void clockTick() {
        if (!moveBall()) {
            updateScore();
            newBall();

            gameState = GameState.ACTIVE_GAME;

            if (player1Score == 10 || player2Score == 10) {
                gameState = GameState.GAME_OVER;
            }
        }
    }

    @Override
    public boolean moveLeftPaddle(int deltaRow) {
        PongPaddle tempPaddle = paddleLeft.shiftedBy(deltaRow);
        if (paddleIsOnBoard(tempPaddle)) {
            paddleLeft = tempPaddle;
            return true;
        }
        return false;
    }

    @Override
    public boolean moveRightPaddle(int deltaRow) {
        PongPaddle tempPaddle = paddleRight.shiftedBy(deltaRow);
        if (paddleIsOnBoard(tempPaddle)) {
            paddleRight = tempPaddle;
            return true;
        }
        return false;
    }

    private boolean paddleIsOnBoard(PongPaddle paddle) {
        for (GridCell cell : paddle) {
            CellPosition pos = cell.pos();
            if (!board.positionIsOnGrid(pos) || !board.get(pos).equals('-')) {
                return false;
            }
        }
        return true;
    }

    private boolean moveBall() {
        PongBall tempBall = pongBall.shiftedBy(deltaY, deltaX);
        if (ballPaddleCollision(tempBall)) {
            deltaX *= -1;
            pongBall = pongBall.shiftedBy(deltaY, deltaX);
            gameState = GameState.ACTIVE_GAME_FAST;
            return true;
        }
        else if (ballWallCollision(tempBall)) {
            deltaY *= -1;
            pongBall = pongBall.shiftedBy(deltaY, deltaX);
            return true;
        }
        else if (ballIsOnBoard(tempBall)) {
            pongBall = tempBall;
            return true;
        }

        return false;
    }


    private boolean ballIsOnBoard(PongBall ball) {
        for (GridCell cell : ball) {
            CellPosition pos = cell.pos();
            if (!board.positionIsOnGrid(pos) || !board.get(pos).equals('-')) {
                return false;
            }
        }
        return true;
    }

    private boolean ballPaddleCollision(PongBall ball) {
        for (GridCell paddles : getPaddlesOnBoard()) {
            for (GridCell ballPosition : ball) {
                if (paddles.pos().equals(ballPosition.pos())) {
                    return true;
                }
            }
        }
        return false;
    }


    private boolean ballWallCollision(PongBall ball) {
        ArrayList<CellPosition> wallPositions = new ArrayList<>();

        for (GridCell walls : getTilesOnBoard()) {
            if (!board.get(walls.pos()).equals('-')) {
                wallPositions.add(walls.pos());
            }
            for (GridCell ballPosition : ball) {
                if (wallPositions.contains(ballPosition.pos())) {
                    return true;
                }
            }
        }
        return false;
    }

    private void updateScore() {
        for (GridCell cell : pongBall) {
            if (cell.pos().col() >= Math.floorDiv(board.cols(), 2)) {
                player1Score += 1;
            }
            if (cell.pos().col() <= Math.floorDiv(board.cols(), 2)) {
                player2Score += 1;
            }
        }
    }

    private void newBall() {
        Random random = new Random();
        PongBall tempBall = ballFactory.getNewBall().shiftedToCenterOfBoard(board);

        int randomDeltaY = random.nextInt(Math.floorDiv(board.rows(), 2) - 2);
        if (random.nextBoolean()){
            randomDeltaY *= -1;
            deltaY *= -1;
        }

        pongBall = tempBall.shiftedBy(randomDeltaY,0);
        deltaX *= -1;

        }
    }



