package no.uib.inf101.pong.model.ball;

import no.uib.inf101.grid.CellPosition;
import no.uib.inf101.grid.GridCell;
import no.uib.inf101.grid.GridDimension;

import java.util.ArrayList;
import java.util.Iterator;

public class PongBall implements Iterable<GridCell> {
    private final char symbol;
    private final boolean[][] shape;
    private final CellPosition position;

    PongBall(char symbol, boolean[][] shape, CellPosition position) {
        this.symbol = symbol;
        this.shape = shape;
        this.position = position;
    }

    public static PongBall newPongBall(char c){
        boolean[][] shape;
        shape = new boolean[][]{
                {false, false, false},
                {false, true, false},
                {false, false, false}
        };
        return new PongBall(c, shape, new CellPosition(0, 0));
    }

    @Override
    public Iterator<GridCell> iterator() {
        ArrayList<GridCell> list = new ArrayList<>();
        for (int row = 0; row < shape.length; row++) {
            for (int col = 0; col < shape[0].length; col++) {
                if (shape[row][col]) {
                    list.add(new GridCell(new CellPosition(
                            position.row() + row, position.col() + col), symbol));
                }
            }
        }
        return list.iterator();
    }

    /**
     * Shifts a PongBall object to the center of a given board
     * @param gridDimension the dimensions of the board
     * @return a PongBall object shifted to the center of a board
     */
    public PongBall shiftedToCenterOfBoard(GridDimension gridDimension) {
        return new PongBall(symbol, shape, new CellPosition(
                Math.floorDiv(gridDimension.rows(), 2) - 1,
                Math.floorDiv(gridDimension.cols(), 2) - 1
        ));
    }

    /**
     * Shifts a PongBall by specified amount
     * @param deltaRow how many rows to shift by
     * @param deltaCol how many cols to shift by
     * @return PongBall shifted by said amount
     */
    public PongBall shiftedBy(int deltaRow, int deltaCol){
        return new PongBall(symbol, shape, new CellPosition(
                position.row() - deltaRow, position.col() - deltaCol
        ));
    }

}
