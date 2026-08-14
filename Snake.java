package com.example;

import javax.swing.JFrame;
import javax.swing.SwingUtilities;


public class Snake {

    public static void main(String[] args) {
        // Rularea interfeței grafice pe firul de execuție EDT (Event Dispatch Thread)
        SwingUtilities.invokeLater(() -> {
            int boardWidth = 500;
            int boardHeight = 500;

            JFrame frame = new JFrame("Snake");
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            frame.setResizable(false);

            SnakeGame snakeGame = new SnakeGame(boardWidth, boardHeight);
            frame.add(snakeGame);
            frame.pack();

            frame.setLocationRelativeTo(null);
            frame.setVisible(true);

            snakeGame.requestFocus();
        });
    }
}
