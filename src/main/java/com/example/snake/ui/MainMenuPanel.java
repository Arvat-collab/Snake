package com.example.snake.ui;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class MainMenuPanel extends JPanel {

    private final JButton startButton;
    private final JButton settingsButton;
    private final JButton quitButton;

    public MainMenuPanel(ActionListener onStart, ActionListener onSettings, ActionListener onQuit) {
        setPreferredSize(new Dimension(650, 650));
        setBackground(Color.DARK_GRAY);
        setLayout(new GridBagLayout());

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(15, 15, 15, 15);
        gbc.gridx = 0;

        // Title Label
        JLabel titleLabel = new JLabel("SNAKE GAME");
        titleLabel.setFont(new Font("Arial", Font.BOLD, 42));
        titleLabel.setForeground(Color.GREEN);
        gbc.gridy = 0;
        add(titleLabel, gbc);

        // Start Button
        startButton = createStyledButton("START GAME");
        startButton.addActionListener(onStart);
        gbc.gridy = 1;
        add(startButton, gbc);

        // Settings Button
        settingsButton = createStyledButton("SETTINGS");
        settingsButton.addActionListener(onSettings);
        gbc.gridy = 2;
        add(settingsButton, gbc);

        // Quit Button
        quitButton = createStyledButton("QUIT");
        quitButton.addActionListener(onQuit);
        gbc.gridy = 3;
        add(quitButton, gbc);
    }

    private JButton createStyledButton(String text) {
        JButton button = new JButton(text);
        button.setFont(new Font("Arial", Font.BOLD, 18));
        button.setPreferredSize(new Dimension(240, 50));
        button.setFocusable(false);
        button.setBackground(Color.LIGHT_GRAY);
        button.setForeground(Color.BLACK);
        return button;
    }
}
