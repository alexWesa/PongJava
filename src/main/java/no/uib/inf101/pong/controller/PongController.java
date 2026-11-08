package no.uib.inf101.pong.controller;

import javax.swing.*;


import no.uib.inf101.pong.model.GameState;
import no.uib.inf101.pong.view.PongView;

import java.awt.event.*;
import java.util.HashSet;
import java.util.Set;

public class PongController implements java.awt.event.KeyListener {

    private ControllablePongModel model;
    private PongView view;

    Set<Integer> keys = new HashSet<Integer>();


    private Timer timer;

    public PongController(ControllablePongModel model, PongView view) {
        this.model = model;
        this.view = view;
        view.addKeyListener(this);
        view.setFocusable(true);

        this.timer = new Timer(model.getTimeInterval(), this::clockTick);
        timer.start();
    }

    @Override
    public void keyTyped(KeyEvent e) {

    }

    @Override
    public void keyPressed(KeyEvent e) {
        keys.add(e.getKeyCode());
        multiKeys();
        view.repaint();
    }

    // Stack Overflow https://stackoverflow.com/questions/67066490/keylistener-how-to-handle-multiple-keys-pressed; 9. April 2025
    private void multiKeys(){
        for (Integer key : keys) {
            switch (key){
                case KeyEvent.VK_UP:
                    model.moveRightPaddle(1);
                    break;
                case KeyEvent.VK_DOWN:
                    model.moveRightPaddle(-1);
                    break;
                case KeyEvent.VK_W:
                    model.moveLeftPaddle(1);
                    break;
                case KeyEvent.VK_S:
                    model.moveLeftPaddle(-1);
                    break;
                case KeyEvent.VK_ESCAPE:
                    System.exit(0);
                    break;
            }
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {
        keys.remove(e.getKeyCode());
    }

    private void clockTick(ActionEvent e){
        if(model.getGameState() == GameState.ACTIVE_GAME){
            model.clockTick();
            updateTimeDelay();
            view.repaint();
        }
        else if (model.getGameState() == GameState.ACTIVE_GAME_FAST){
            model.clockTick();
            updateTimeDelay();
            view.repaint();
        }
    }

    private void updateTimeDelay(){
        int delay = model.getTimeInterval();
        timer.setDelay(delay);
        timer.setInitialDelay(delay);
    }
}
