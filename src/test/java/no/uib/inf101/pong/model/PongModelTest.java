package no.uib.inf101.pong.model;

import no.uib.inf101.grid.CellPosition;
import no.uib.inf101.grid.GridCell;
import no.uib.inf101.grid.GridDimension;
import no.uib.inf101.pong.model.paddle.PongPaddle;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

public class PongModelTest {

    @Test
    public void leftPaddleMovedTest() {
        PongModel model = new PongModel();

        assertTrue(model.moveLeftPaddle(1));
        assertTrue(model.moveLeftPaddle(-1));
        assertFalse(model.moveLeftPaddle(30));
    }

    @Test
    public void rightPaddleMovedTest() {
        PongModel model = new PongModel();

        assertTrue(model.moveRightPaddle(1));
        assertTrue(model.moveRightPaddle(-1));
        assertFalse(model.moveRightPaddle(30));
    }

    @Test
    public void ballIsOnBoardTest() {
        PongModel model = new PongModel();
        ArrayList<GridCell> ball = new ArrayList<>();

        for (GridCell c : model.getBallOnBoard()){
            ball.add(c);
        }
        assertFalse(ball.isEmpty());
    }

    @Test
    public void paddleIsOnBoardTest() {
        PongModel model = new PongModel();
        ArrayList<GridCell> paddles = new ArrayList<>();

        for (GridCell c : model.getPaddlesOnBoard()){
            paddles.add(c);
        }
        assertFalse(paddles.isEmpty());
    }

    @Test
    public void gameOverTest() {
        PongModel model = new PongModel();

        while(!model.getGameState().equals(GameState.GAME_OVER)){
            model.clockTick();
        }
        assertEquals(GameState.GAME_OVER, model.getGameState());
    }

    @Test
    public void scoreTest() {
        PongModel model = new PongModel();

        assertEquals("0 - 0", model.getScore());

        while(model.getScore().equals("0 - 0")){
            model.clockTick();
        }
        assertEquals("0 - 1", model.getScore());
    }

    @Test
    public void clockTickTest() {
        PongModel model = new PongModel();
        CellPosition ballPos = new CellPosition(0,0);
        CellPosition newBallPos = new CellPosition(0,0);

        for (GridCell c : model.getBallOnBoard()){
            ballPos = c.pos();
        }

        model.clockTick();

        for (GridCell cell : model.getBallOnBoard()){
            newBallPos = cell.pos();
        }
        assertNotEquals(newBallPos, ballPos);
    }

    @Test
    public void gridDimensionTest() {
        PongModel model = new PongModel();
        PongBoard board = new PongBoard(15, 25);
        int expectedRows = board.rows();
        int expectedCols = board.cols();

        int actualRows = model.getDimension().rows();
        int actualCols = model.getDimension().cols();

        assertEquals(expectedRows, actualRows);
        assertEquals(expectedCols, actualCols);
    }

}
