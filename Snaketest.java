package com.example;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
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
    @Test 
    public void snakegrowsapple() {
       Snakerer game = new Snakerer(500,500);
       game.apple.x = game.snakehead.x;
       game.apple.y = game.snakehead.y;

       int sizebeforeapple = game.snakebody.size();

       game.move();
       assertEquals(sizebeforeapple +1, game.snakebody.size());

    }
    @Test 
    public void applemovement(){
          Snakerer game = new Snakerer(500,500);
          game.apple.x = game.snakehead.x;
          game.apple.x = game.snakehead.y;

          game.move();

          assertFalse(game.apple.x == game.snakehead.x &&
                      game.apple.y == game.snakehead.y);



    }
}
