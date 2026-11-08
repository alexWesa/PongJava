package no.uib.inf101.pong.view;

import no.uib.inf101.grid.GridCell;

import java.awt.*;

public class ColorTheme {

    public Color getCellColor(Character c) {
        Color color = switch (c) {
            case 'r' -> Color.RED;
            case 'g' -> new Color(165, 165, 165, 117);
                    //Color.LIGHT_GRAY;
            case 'y' -> Color.YELLOW;
            case 'b' -> Color.BLUE;
            case 'w' -> Color.WHITE;
            case '-' -> Color.BLACK;

            default -> throw new IllegalArgumentException(
                    "No available color for '" + c + "'");
        };
        return color;
    }

    public Color getFrameColor() {
        return new Color(0,0,0,0);
        //return Color.DARK_GRAY;
    }

    public Color getBackgroundColor() {
        return new Color(0, 0, 0, 128);
    }

    public Color getTextColor() {
        return new Color(215, 238, 255, 129);
    }

}
