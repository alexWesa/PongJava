package no.uib.inf101.pong.model;

import no.uib.inf101.grid.CellPosition;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class PongBoardTest {

    @Test
    public void testBoardWalls() {
        PongBoard board = new PongBoard(15,25);
        char expected = 'g';
        for (int row = 0; row < board.rows(); row++) {
            for (int col = 0; col < board.cols(); col++) {
                if (row == 0 || row == board.rows() - 1) {
                    char actual = board.get(new CellPosition(row,col));
                    assertEquals(expected, actual);
                }
            }
        }
    }
}
