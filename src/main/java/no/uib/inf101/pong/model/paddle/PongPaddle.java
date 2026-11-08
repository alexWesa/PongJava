package no.uib.inf101.pong.model.paddle;


import no.uib.inf101.grid.CellPosition;
import no.uib.inf101.grid.GridCell;
import no.uib.inf101.grid.GridDimension;

import java.util.ArrayList;
import java.util.Iterator;

public class PongPaddle implements Iterable<GridCell>{
    private final char symbol;
    private final boolean[][] shape;
    private final CellPosition position;

    private PongPaddle(char symbol, boolean[][] shape, CellPosition position) {
        this.symbol = symbol;
        this.shape = shape;
        this.position = position;
    }

    public static PongPaddle paddleShape(char c){
        boolean[][] shape;
        shape = new boolean[][]{
                {false, true, false},
                {false, true, false},
                {false, true, false},
                {false, true, false}
        };
        return new PongPaddle(c, shape,
                new CellPosition(0, 0));
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
     * Shifts a PongPaddle-object to the left center of a grid
     * @param gridDimension the dimension of the grid
     * @return new PongPaddle-object moved to the left center of the grid
     */
    public PongPaddle shiftedToLeftCenterOf(GridDimension gridDimension) {
        int center = gridDimension.rows()/2;
        return new PongPaddle(symbol, shape, new CellPosition(center - shape.length/2, -1));
    }

    /**
     * Shifts a PongPaddle-object to the right center of a grid
     * @param gridDimension the dimension of the grid
     * @return new PongPaddle-object moved to the right center of the grid
     */
    public PongPaddle shiftedToRightCenterOf(GridDimension gridDimension) {
        int center = gridDimension.rows()/2;
        return new PongPaddle(symbol, shape, new CellPosition(
                center - shape.length/2, gridDimension.cols()-2));
    }

    /**
     * Shifts a paddle
     * @param deltaRow how much the paddle is shifted by
     * @return a new shifted PongPaddle-object
     */
    public PongPaddle shiftedBy(int deltaRow){
        return new PongPaddle(symbol, shape, new CellPosition(
                position.row() - deltaRow, position.col()));
    }

}
