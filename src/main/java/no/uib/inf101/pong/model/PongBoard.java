package no.uib.inf101.pong.model;

import no.uib.inf101.grid.CellPosition;
import no.uib.inf101.grid.Grid;

public class PongBoard extends Grid {

    public PongBoard(int rows, int cols) {
        super(rows, cols, '-');

        for(int row = 0; row < rows; row++) {
            for(int col = 0; col < cols; col++) {
                if(row == 0 || row == rows - 1){
                    set(new CellPosition(row, col), 'g');
                }
            }
        }

    }

}

