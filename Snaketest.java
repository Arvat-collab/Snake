package com.example;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class Snaketest {
    @Test
    public void snakecollision() {
            Snakerer game = new Snakerer(500,500);
            game.snakehead.x = 0;
            game.snakehead.y = 5;
            
            game.speedX = -1;
            game.speedY = 0;
            game.move();
            assertTrue(game.gameOver);

    }
}
