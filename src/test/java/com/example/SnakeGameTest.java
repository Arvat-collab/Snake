package com.example;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;


public class SnakeGameTest {

    @Test
    public void testSnakeCollision() {
        SnakeGame game = new SnakeGame(500, 500);

        game.getSnakeHead().setX(0);
        game.getSnakeHead().setY(5);

        game.setSpeedX(-1);
        game.setSpeedY(0);

        game.move();

        assertTrue(game.isGameOver());
    }

    @Test
    public void testSnakeGrowsAfterEatingApple() {
        SnakeGame game = new SnakeGame(500, 500);

        game.getApple().getPosition().setX(game.getSnakeHead().getX());
        game.getApple().getPosition().setY(game.getSnakeHead().getY());

        int sizeBefore = game.getSnakeBody().size();

        game.move();

        assertEquals(sizeBefore + 1, game.getSnakeBody().size());
    }

    @Test
    public void testAppleMovesAfterBeingEaten() {
        SnakeGame game = new SnakeGame(500, 500);

        game.getApple().getPosition().setX(game.getSnakeHead().getX());
        game.getApple().getPosition().setY(game.getSnakeHead().getY());

        game.move();

        assertFalse(
                game.getApple().getPosition().getX() == game.getSnakeHead().getX()
                && game.getApple().getPosition().getY() == game.getSnakeHead().getY()
        );
    }
}
