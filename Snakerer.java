package com.example;

import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;
import java.util.Random;
import javax.swing.*;

public class Snakerer extends JPanel implements ActionListener, KeyListener {

    private final int boardWidth;
    private final int boardHeight;
    private final int cellSize = 25;

    private final Random random;
    private final Timer gameLoop;

    private final Fruit apple;
    private final Tile snakeHead;
    private final ArrayList<Tile> snakeBody;

    private int speedX;
    private int speedY;

    boolean gameOver = false;

    public Snakerer(int boardWidth, int boardHeight) {
        this.boardWidth = boardWidth;
        this.boardHeight = boardHeight;

        setPreferredSize(new Dimension(boardWidth, boardHeight));
        setBackground(Color.PINK);

        random = new Random();

        snakeHead = new Tile(5, 5);
        apple = new Fruit(15, 15);

        snakeBody = new ArrayList<>();

        speedX = 0;
        speedY = 1;

        gameLoop = new Timer(67, this);
        gameLoop.start();

        addKeyListener(this);
        setFocusable(true);
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        draw(g);

        g.setFont(new Font("Arial", Font.BOLD, 16));

        if (gameOver) {
            g.setColor(Color.RED);
            g.drawString("GAME OVER! Score: " + snakeBody.size(), 10, 20);
        } else {
            g.setColor(Color.BLACK);
            g.drawString("Score: " + snakeBody.size(), 10, 20);
        }
    }

    private void draw(Graphics g) {

        // Grid
        g.setColor(Color.LIGHT_GRAY);

        for (int i = 0; i < boardWidth / cellSize; i++) {
            g.drawLine(i * cellSize, 0, i * cellSize, boardHeight);
        }

        for (int i = 0; i < boardHeight / cellSize; i++) {
            g.drawLine(0, i * cellSize, boardWidth, i * cellSize);
        }

        // Apple
        g.setColor(Color.RED);
        g.fillRect(
                apple.getPosition().getX() * cellSize,
                apple.getPosition().getY() * cellSize,
                cellSize,
                cellSize);

        // Snake head
        g.setColor(Color.BLUE);
        g.fillRect(
                snakeHead.getX() * cellSize,
                snakeHead.getY() * cellSize,
                cellSize,
                cellSize);

        // Snake body
        g.setColor(Color.BLACK);

        for (Tile bodyPart : snakeBody) {
            g.fillRect(
                    bodyPart.getX() * cellSize,
                    bodyPart.getY() * cellSize,
                    cellSize,
                    cellSize);
        }
    }

    public void move() {

        if (snakeHead.collides(apple.getPosition())) {
            snakeBody.add(new Tile(
                    apple.getPosition().getX(),
                    apple.getPosition().getY()));

            apple.place(random, boardWidth, boardHeight, cellSize);
        }

        for (int i = snakeBody.size() - 1; i > 0; i--) {
            Tile current = snakeBody.get(i);
            Tile previous = snakeBody.get(i - 1);

            current.setX(previous.getX());
            current.setY(previous.getY());
        }

        if (!snakeBody.isEmpty()) {
            snakeBody.get(0).setX(snakeHead.getX());
            snakeBody.get(0).setY(snakeHead.getY());
        }

        snakeHead.setX(snakeHead.getX() + speedX);
        snakeHead.setY(snakeHead.getY() + speedY);

        if (snakeHead.getX() < 0
                || snakeHead.getX() >= boardWidth / cellSize
                || snakeHead.getY() < 0
                || snakeHead.getY() >= boardHeight / cellSize) {

            gameOver = true;
        }

        for (Tile bodyPart : snakeBody) {
            if (snakeHead.collides(bodyPart)) {
                gameOver = true;
            }
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        move();
        repaint();

        if (gameOver) {
            gameLoop.stop();
        }
    }

    @Override
    public void keyPressed(KeyEvent e) {

        if (e.getKeyCode() == KeyEvent.VK_UP && speedY != 1) {
            speedX = 0;
            speedY = -1;
        } else if (e.getKeyCode() == KeyEvent.VK_DOWN && speedY != -1) {
            speedX = 0;
            speedY = 1;
        } else if (e.getKeyCode() == KeyEvent.VK_LEFT && speedX != 1) {
            speedX = -1;
            speedY = 0;
        } else if (e.getKeyCode() == KeyEvent.VK_RIGHT && speedX != -1) {
            speedX = 1;
            speedY = 0;
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {}

    @Override
    public void keyTyped(KeyEvent e) {}
    public Tile getSnakeHead() {
    return snakeHead;
}

public ArrayList<Tile> getSnakeBody() {
    return snakeBody;
}

public Fruit getApple() {
    return apple;
}

public boolean isGameOver() {
    return gameOver;
}

public void setSpeedX(int speedX) {
    this.speedX = speedX;
}

public void setSpeedY(int speedY) {
    this.speedY = speedY;
}
}
