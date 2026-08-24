package com.example;

import java.util.ArrayList;
import java.util.List;


public class SnakeBody {

    private final Tile head;
    private final List<Tile> body;
    private int speedX;
    private int speedY;

    public SnakeBody(int startX, int startY) {
        this.head = new Tile(startX, startY);
        this.body = new ArrayList<>();
        this.speedX = 0;
        this.speedY = 1;
    }

    public Tile getHead() {
        return head;
    }

    public List<Tile> getBody() {
        return body;
    }

    public int getSpeedX() {
        return speedX;
    }

    public void setSpeedX(int speedX) {
        this.speedX = speedX;
    }

    public int getSpeedY() {
        return speedY;
    }

    public void setSpeedY(int speedY) {
        this.speedY = speedY;
    }
}
