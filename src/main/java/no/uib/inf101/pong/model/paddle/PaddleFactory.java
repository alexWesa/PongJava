package no.uib.inf101.pong.model.paddle;

public interface PaddleFactory {

    /**
     * Gets a new paddle
     * @return a new paddle
     */
    PongPaddle getNewPaddle();
}
