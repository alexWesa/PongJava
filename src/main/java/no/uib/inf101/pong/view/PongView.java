package no.uib.inf101.pong.view;

import no.uib.inf101.grid.CellPosition;
import no.uib.inf101.grid.GridCell;
import no.uib.inf101.grid.GridDimension;
import no.uib.inf101.pong.model.GameState;

import javax.swing.*;
import java.awt.*;
import java.awt.geom.Rectangle2D;

public class PongView extends JPanel {

    public static final int OUTERMARGIN = 15;
    private static final int CELLMARGIN = 2;
    public static final int PREFERREDSIDESIZE = 40;

    private ViewablePongModel viewablePongModel;
    private ColorTheme colorTheme;

    public PongView(ViewablePongModel viewablePongModel) {
        this.viewablePongModel = viewablePongModel;

        this.colorTheme = new ColorTheme();
        this.setBackground(colorTheme.getBackgroundColor());

        this.setFocusable(true);
        this.setPreferredSize(getDefaultSize(viewablePongModel.getDimension()));
    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2 = (Graphics2D) g;
        drawGame(g2);
    }

    private void drawGame(Graphics2D g2) {
        Rectangle2D box = new Rectangle2D.Double(
                OUTERMARGIN,
                OUTERMARGIN,
                this.getWidth() - OUTERMARGIN * 2,
                this.getHeight() - OUTERMARGIN * 2);
        g2.setColor(colorTheme.getFrameColor());
        g2.fill(box);

        CellPositionToPixelConverter converter = new CellPositionToPixelConverter(box,
                viewablePongModel.getDimension(), CELLMARGIN);

        drawCells(g2, viewablePongModel.getTilesOnBoard(), converter, colorTheme);

        drawCells(g2, viewablePongModel.getPaddlesOnBoard(), converter, colorTheme);

        drawCells(g2, viewablePongModel.getBallOnBoard(), converter, colorTheme);

        drawScore(g2);

        if (viewablePongModel.getGameState() == GameState.GAME_OVER) {
            drawGameOver(g2);
        }
    }


    private static void drawCells(Graphics2D g2, Iterable<GridCell> iterable,
                                  CellPositionToPixelConverter converter, ColorTheme colorTheme) {
        for (GridCell cell : iterable) {
            CellPosition pos = cell.pos();
            Character c = cell.symbol();
            Rectangle2D tile = converter.getBoundsForCell(pos);

            g2.setColor(colorTheme.getCellColor(c));
            g2.fill(tile);
        }
    }

    private static Dimension getDefaultSize(GridDimension gd) {
        int width = (int) (PREFERREDSIDESIZE * gd.cols() + CELLMARGIN * (gd.cols() + 1) + 2 * OUTERMARGIN);
        int height = (int) (PREFERREDSIDESIZE * gd.rows() + CELLMARGIN * (gd.cols() + 1) + 2 * OUTERMARGIN);
        return new Dimension(width, height);
    }

    private void drawScore(Graphics2D g2){
        g2.setColor(colorTheme.getTextColor());
        g2.setFont(new Font("AR Darling", Font.BOLD, 55));
        Inf101Graphics.drawCenteredString(g2, viewablePongModel.getScore(), getBounds());
    }

    private void drawGameOver(Graphics2D g2){
        Rectangle2D box = new Rectangle2D.Double(
                0,
                0,
                this.getWidth(),
                this.getHeight());
        g2.setColor(colorTheme.getBackgroundColor());
        g2.fill(box);

        g2.setColor(Color.WHITE);
        g2.setFont(new Font("Monospaced", Font.BOLD, 50));
        Inf101Graphics.drawCenteredString(g2, "GAME OVER", getBounds());
    }

}

