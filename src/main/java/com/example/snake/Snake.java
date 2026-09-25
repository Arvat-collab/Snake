package com.example.snake;

import com.example.snake.ui.MainMenuPanel;
import com.example.snake.ui.SettingsPanel;
import com.example.snake.ui.SnakeGame;

import java.awt.CardLayout;
import java.awt.Dimension;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;
//PS:hit ESC to go back to main menu
public class Snake {

    private static JFrame frame;
    private static CardLayout cardLayout;
    private static JPanel container;
    private static SnakeGame snakeGame;
    private static SettingsPanel settingsPanel;
    private static boolean isFullscreen = false;

    private static final int BOARD_WIDTH = 650;
    private static final int BOARD_HEIGHT = 650;

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            frame = new JFrame("Snake Game");
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            frame.setResizable(false);

            cardLayout = new CardLayout();
            container = new JPanel(cardLayout);
            container.setPreferredSize(new Dimension(BOARD_WIDTH, BOARD_HEIGHT));

            snakeGame = new SnakeGame(BOARD_WIDTH, BOARD_HEIGHT);
            snakeGame.setOnReturnToMenu(() -> showCard("MENU"));

            MainMenuPanel menuPanel = new MainMenuPanel(
                    e -> {
                        showCard("GAME");
                        snakeGame.startGame();
                        snakeGame.requestFocus();
                    },
                    e -> showCard("SETTINGS"),
                    e -> System.exit(0)
            );

            settingsPanel = new SettingsPanel(
                    e -> setFullscreenMode(false),
                    e -> setFullscreenMode(true),
                    e -> showCard("MENU")
            );

            container.add(menuPanel, "MENU");
            container.add(settingsPanel, "SETTINGS");
            container.add(snakeGame, "GAME");

            frame.add(container);
            frame.pack();
            frame.setLocationRelativeTo(null);
            frame.setVisible(true);
        });
    }

    private static void showCard(String cardName) {
        cardLayout.show(container, cardName);
    }

    private static void setFullscreenMode(boolean fullscreen) {
        if (isFullscreen == fullscreen) {
            return;
        }
        isFullscreen = fullscreen;

        frame.dispose();

        if (fullscreen) {
            frame.setUndecorated(true);
            frame.setExtendedState(JFrame.MAXIMIZED_BOTH);
            frame.setVisible(true);
        } else {
            frame.setUndecorated(false);
            frame.setExtendedState(JFrame.NORMAL);
            container.setPreferredSize(new Dimension(BOARD_WIDTH, BOARD_HEIGHT));
            frame.pack();
            frame.setLocationRelativeTo(null);
            frame.setVisible(true);
        }
        settingsPanel.setFullscreenSelected(fullscreen);
    }
}
