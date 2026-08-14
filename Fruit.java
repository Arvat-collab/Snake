package com.example;

import java.util.Random;


public class Fruit {

    private final Tile position;

    public Fruit(int x, int y) {
        this.position = new Tile(x, y);
    }

    public Tile getPosition() {
        return position;
    }

    
    public void place(Random random, int width, int height, int cellSize) {
        int maxGridX = width / cellSize;
        int maxGridY = height / cellSize;
        this.position.setX(random.nextInt(maxGridX));
        this.position.setY(random.nextInt(maxGridY));
    }
}
