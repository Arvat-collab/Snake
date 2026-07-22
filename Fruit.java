package com.example;
import java.util.Random;
public class Fruit {
      private Tile position;

    public Fruit(int x, int y) {
        position = new Tile(x, y);
    }

    public Tile getPosition() {
        return position;
    }

    public void place(Random random, int width, int height, int cellSize) {
        position.setX(random.nextInt(width / cellSize));
        position.setY(random.nextInt(height / cellSize));
    }
    
}
