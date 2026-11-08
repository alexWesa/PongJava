package no.uib.inf101.pong.model.ball;

public interface BallFactory {

    /**
     * Gets a new PongBall-object
     * @return a new PongBall-object
     */
    PongBall getNewBall();
}
