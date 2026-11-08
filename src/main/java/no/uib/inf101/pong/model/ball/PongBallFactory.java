package no.uib.inf101.pong.model.ball;

public class PongBallFactory implements BallFactory {

    @Override
    public PongBall getNewBall() {
        char c = 'w';
        return PongBall.newPongBall(c);
    }
}
