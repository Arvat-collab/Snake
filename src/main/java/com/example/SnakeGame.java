package com.example;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.ArrayList;
import java.util.Random;
import javax.swing.JPanel;
import javax.swing.Timer;


public class SnakeGame extends JPanel implements ActionListener, KeyListener {

    private static final int CELL_SIZE = 25;
    private static final int TIMER_DELAY_MS = 67;

    private final int boardWidth;
    private final int boardHeight;

    private final Random random;
    private final Timer gameLoop;

    private final Fruit apple;
    private final SnakeBody snake;

    private boolean gameOver;

    public SnakeGame(int boardWidth, int boardHeight) {
        this.boardWidth = boardWidth;
        this.boardHeight = boardHeight;
        this.gameOver = false;

        setPreferredSize(new Dimension(boardWidth, boardHeight));
        setBackground(Color.PINK);

        this.random = new Random();

       
        this.snake = new SnakeBody(5, 5);

  
        this.apple = new Fruit(15, 15);

        this.gameLoop = new Timer(TIMER_DELAY_MS, this);
        this.gameLoop.start();

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
            g.drawString("GAME OVER! Score: " + snake.getBody().size(), 10, 20);
        } else {
            g.setColor(Color.BLACK);
            g.drawString("Score: " + snake.getBody().size(), 10, 20);
        }
    }

    private void draw(Graphics g) {
        drawGrid(g);
        drawApple(g);
        drawSnake(g);
    }

    private void drawGrid(Graphics g) {
        g.setColor(Color.LIGHT_GRAY);

        for (int i = 0; i < boardWidth / CELL_SIZE; i++) {
            g.drawLine(i * CELL_SIZE, 0, i * CELL_SIZE, boardHeight);
        }

        for (int i = 0; i < boardHeight / CELL_SIZE; i++) {
            g.drawLine(0, i * CELL_SIZE, boardWidth, i * CELL_SIZE);
        }
    }

    private void drawApple(Graphics g) {
        g.setColor(Color.RED);
        g.fillRect(
                apple.getPosition().getX() * CELL_SIZE,
                apple.getPosition().getY() * CELL_SIZE,
                CELL_SIZE,
                CELL_SIZE);
    }

    private void drawSnake(Graphics g) {
     
        g.setColor(Color.BLUE);
        g.fillRect(
                snake.getHead().getX() * CELL_SIZE,
                snake.getHead().getY() * CELL_SIZE,
                CELL_SIZE,
                CELL_SIZE);

        g.setColor(Color.BLACK);
        for (Tile bodyPart : snake.getBody()) {
            g.fillRect(
                    bodyPart.getX() * CELL_SIZE,
                    bodyPart.getY() * CELL_SIZE,
                    CELL_SIZE,
                    CELL_SIZE);
        }
    }

    public void move() {
        if (gameOver) {
            return;
        }

        Tile head = snake.getHead();
        ArrayList<Tile> body = (ArrayList<Tile>) snake.getBody();

        if (head.collides(apple.getPosition())) {
            body.add(new Tile(
                    apple.getPosition().getX(),
                    apple.getPosition().getY()));

            apple.place(random, boardWidth, boardHeight, CELL_SIZE);
        }

        for (int i = body.size() - 1; i > 0; i--) {
            Tile current = body.get(i);
            Tile previous = body.get(i - 1);

            current.setX(previous.getX());
            current.setY(previous.getY());
        }

        if (!body.isEmpty()) {
            body.get(0).setX(head.getX());
            body.get(0).setY(head.getY());
        }

        
        head.setX(head.getX() + snake.getSpeedX());
        head.setY(head.getY() + snake.getSpeedY());

        
        if (head.getX() < 0
                || head.getX() >= boardWidth / CELL_SIZE
                || head.getY() < 0
                || head.getY() >= boardHeight / CELL_SIZE) {
            gameOver = true;
        }

        // Verificare coliziune cu propriul corp
        for (Tile bodyPart : body) {
            if (head.collides(bodyPart)) {
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
        int key = e.getKeyCode();

        if (key == KeyEvent.VK_UP && snake.getSpeedY() != 1) {
            snake.setSpeedX(0);
            snake.setSpeedY(-1);
        } else if (key == KeyEvent.VK_DOWN && snake.getSpeedY() != -1) {
            snake.setSpeedX(0);
            snake.setSpeedY(1);
        } else if (key == KeyEvent.VK_LEFT && snake.getSpeedX() != 1) {
            snake.setSpeedX(-1);
            snake.setSpeedY(0);
        } else if (key == KeyEvent.VK_RIGHT && snake.getSpeedX() != -1) {
            snake.setSpeedX(1);
            snake.setSpeedY(0);
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {
        // Nu este necesară nicio acțiune pe eliberarea tastei
    }

    @Override
    public void keyTyped(KeyEvent e) {
        // Nu este necesară nicio acțiune pe tastare
    }

    // Getter-e și Setter-e pentru compatibilitate și încapsulare
    public Tile getSnakeHead() {
        return snake.getHead();
    }

    public ArrayList<Tile> getSnakeBody() {
        return (ArrayList<Tile>) snake.getBody();
    }

    public Fruit getApple() {
        return apple;
    }

    public boolean isGameOver() {
        return gameOver;
    }

    public void setSpeedX(int speedX) {
        snake.setSpeedX(speedX);
    }

    public void setSpeedY(int speedY) {
        snake.setSpeedY(speedY);
    }
}
