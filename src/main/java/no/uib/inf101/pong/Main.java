package no.uib.inf101.pong;

import no.uib.inf101.pong.controller.PongController;
import no.uib.inf101.pong.model.PongModel;
import no.uib.inf101.pong.view.PongView;

import javax.swing.*;
import java.awt.*;

public class Main {

    public static final String WINDOW_TITLE = "Pong";

    public static void main(String[] args) {
        PongModel model = new PongModel();
        PongView view = new PongView(model);
        new PongController(model, view);

        JFrame frame = new JFrame(WINDOW_TITLE);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setContentPane(view);
        frame.pack();
        frame.setVisible(true);
        // Open GUI in the middle of the screen
        Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
        frame.setLocation(dim.width / 2 - frame.getSize().width / 2, dim.height / 2 - frame.getSize().height / 2);

    }
}
