package no.uib.inf101.pong.model.paddle;

public class PatternedPaddleFactory implements PaddleFactory {

    @Override
    public PongPaddle getNewPaddle() {
        char c = 'w';
        return PongPaddle.paddleShape(c);
    }
}
