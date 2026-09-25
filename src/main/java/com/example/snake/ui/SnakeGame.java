package com.example.snake.ui;

import com.example.snake.model.Fruit;
import com.example.snake.model.SnakeBody;
import com.example.snake.model.Tile;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.List;
import java.util.Random;
import javax.swing.JPanel;
import javax.swing.Timer;

public class SnakeGame extends JPanel implements ActionListener, KeyListener {

    private static final int CELL_SIZE = 25;
    private static final int TIMER_DELAY_MS = 140;

    private final int boardWidth;
    private final int boardHeight;

    private final Random random;
    private final Timer gameLoop;

    private final Fruit apple;
    private final SnakeBody snake;

    private boolean gameOver;
    private Runnable onReturnToMenu;

    public SnakeGame(int boardWidth, int boardHeight) {
        this.boardWidth = boardWidth;
        this.boardHeight = boardHeight;
        this.gameOver = false;

        setPreferredSize(new Dimension(boardWidth, boardHeight));
        setBackground(Color.DARK_GRAY);

        this.random = new Random();
        this.snake = new SnakeBody(5, 5);
        this.apple = new Fruit(15, 15);

        this.gameLoop = new Timer(TIMER_DELAY_MS, this);

        addKeyListener(this);
        setFocusable(true);
    }

    public void setOnReturnToMenu(Runnable onReturnToMenu) {
        this.onReturnToMenu = onReturnToMenu;
    }

    public void startGame() {
        resetGame();
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        int offsetX = Math.max(0, (getWidth() - boardWidth) / 2);
        int offsetY = Math.max(0, (getHeight() - boardHeight) / 2);

        // Fill full screen background
        g.setColor(Color.DARK_GRAY);
        g.fillRect(0, 0, getWidth(), getHeight());

        // Fill board background
        g.setColor(Color.PINK);
        g.fillRect(offsetX, offsetY, boardWidth, boardHeight);

        draw(g, offsetX, offsetY);

        if (gameOver) {
            drawGameOver(g, offsetX, offsetY);
        } else {
            g.setFont(new Font("Arial", Font.BOLD, 16));
            g.setColor(Color.BLACK);
            g.drawString("Score: " + snake.getBody().size(), offsetX + 10, offsetY + 25);
        }
    }

    private void drawGameOver(Graphics g, int offsetX, int offsetY) {
        // 1. Semi-transparent dark overlay
        g.setColor(new Color(0, 0, 0, 170));
        g.fillRect(offsetX, offsetY, boardWidth, boardHeight);

        // 2. Centered "GAME OVER" title
        g.setFont(new Font("Arial", Font.BOLD, 36));
        g.setColor(Color.RED);
        String title = "GAME OVER";
        int titleX = offsetX + (boardWidth - g.getFontMetrics().stringWidth(title)) / 2;
        g.drawString(title, titleX, offsetY + boardHeight / 2 - 50);

        // 3. Centered Final Score
        g.setFont(new Font("Arial", Font.BOLD, 20));
        g.setColor(Color.WHITE);
        String scoreText = "Final Score: " + snake.getBody().size();
        int scoreX = offsetX + (boardWidth - g.getFontMetrics().stringWidth(scoreText)) / 2;
        g.drawString(scoreText, scoreX, offsetY + boardHeight / 2);

        // 4. Centered Restart / Menu prompt
        g.setFont(new Font("Arial", Font.BOLD, 16));
        g.setColor(Color.YELLOW);
        String restartText = "Press [ R ] Restart  |  [ ESC ] Main Menu";
        int restartX = offsetX + (boardWidth - g.getFontMetrics().stringWidth(restartText)) / 2;
        g.drawString(restartText, restartX, offsetY + boardHeight / 2 + 50);
    }

    private void draw(Graphics g, int offsetX, int offsetY) {
        drawGrid(g, offsetX, offsetY);
        drawApple(g, offsetX, offsetY);
        drawSnake(g, offsetX, offsetY);
    }

    private void drawGrid(Graphics g, int offsetX, int offsetY) {
        g.setColor(Color.LIGHT_GRAY);

        for (int i = 0; i <= boardWidth / CELL_SIZE; i++) {
            g.drawLine(offsetX + i * CELL_SIZE, offsetY, offsetX + i * CELL_SIZE, offsetY + boardHeight);
        }

        for (int i = 0; i <= boardHeight / CELL_SIZE; i++) {
            g.drawLine(offsetX, offsetY + i * CELL_SIZE, offsetX + boardWidth, offsetY + i * CELL_SIZE);
        }
    }

    private void drawApple(Graphics g, int offsetX, int offsetY) {
        g.setColor(Color.RED);
        g.fillRect(
                offsetX + apple.getPosition().getX() * CELL_SIZE,
                offsetY + apple.getPosition().getY() * CELL_SIZE,
                CELL_SIZE,
                CELL_SIZE);
    }

    private void drawSnake(Graphics g, int offsetX, int offsetY) {
        g.setColor(Color.BLUE);
        g.fillRect(
                offsetX + snake.getHead().getX() * CELL_SIZE,
                offsetY + snake.getHead().getY() * CELL_SIZE,
                CELL_SIZE,
                CELL_SIZE);

        g.setColor(Color.BLACK);
        for (Tile bodyPart : snake.getBody()) {
            g.fillRect(
                    offsetX + bodyPart.getX() * CELL_SIZE,
                    offsetY + bodyPart.getY() * CELL_SIZE,
                    CELL_SIZE,
                    CELL_SIZE);
        }
    }

    public void move() {
        if (gameOver) {
            return;
        }

        Tile head = snake.getHead();
        List<Tile> body = snake.getBody();

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

        if (key == KeyEvent.VK_R) {
            resetGame();
            return;
        }

        if (key == KeyEvent.VK_ESCAPE) {
            if (gameLoop.isRunning()) {
                gameLoop.stop();
            }
            if (onReturnToMenu != null) {
                onReturnToMenu.run();
            }
            return;
        }

        if (gameOver) {
            return;
        }

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
    }

    @Override
    public void keyTyped(KeyEvent e) {
    }

    public Tile getSnakeHead() {
        return snake.getHead();
    }

    public List<Tile> getSnakeBody() {
        return snake.getBody();
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

    public void resetGame() {
        snake.reset(5, 5);
        apple.getPosition().setX(15);
        apple.getPosition().setY(15);
        gameOver = false;
        if (!gameLoop.isRunning()) {
            gameLoop.start();
        }
        repaint();
    }
}