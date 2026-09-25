package com.example.snake.ui;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionListener;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;

public class SettingsPanel extends JPanel {

    private final JRadioButton windowedRadio;
    private final JRadioButton fullscreenRadio;
    private final JButton backButton;

    public SettingsPanel(ActionListener onWindowed, ActionListener onFullscreen, ActionListener onBack) {
        setPreferredSize(new Dimension(650, 650));
        setBackground(Color.DARK_GRAY);
        setLayout(new GridBagLayout());

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(15, 15, 15, 15);
        gbc.gridx = 0;

        // Title Label
        JLabel titleLabel = new JLabel("SETTINGS");
        titleLabel.setFont(new Font("Arial", Font.BOLD, 36));
        titleLabel.setForeground(Color.CYAN);
        gbc.gridy = 0;
        add(titleLabel, gbc);

        // Display Mode Section
        JLabel modeLabel = new JLabel("Display Mode:");
        modeLabel.setFont(new Font("Arial", Font.BOLD, 22));
        modeLabel.setForeground(Color.WHITE);
        gbc.gridy = 1;
        add(modeLabel, gbc);

        windowedRadio = new JRadioButton("Windowed Mode (650x650)");
        windowedRadio.setFont(new Font("Arial", Font.PLAIN, 18));
        windowedRadio.setForeground(Color.WHITE);
        windowedRadio.setBackground(Color.DARK_GRAY);
        windowedRadio.setSelected(true);
        windowedRadio.addActionListener(onWindowed);

        fullscreenRadio = new JRadioButton("Fullscreen Mode");
        fullscreenRadio.setFont(new Font("Arial", Font.PLAIN, 18));
        fullscreenRadio.setForeground(Color.WHITE);
        fullscreenRadio.setBackground(Color.DARK_GRAY);
        fullscreenRadio.addActionListener(onFullscreen);

        ButtonGroup group = new ButtonGroup();
        group.add(windowedRadio);
        group.add(fullscreenRadio);

        gbc.gridy = 2;
        add(windowedRadio, gbc);
        gbc.gridy = 3;
        add(fullscreenRadio, gbc);

        // Back Button
        backButton = new JButton("BACK TO MENU");
        backButton.setFont(new Font("Arial", Font.BOLD, 18));
        backButton.setPreferredSize(new Dimension(240, 50));
        backButton.setFocusable(false);
        backButton.setBackground(Color.LIGHT_GRAY);
        backButton.setForeground(Color.BLACK);
        backButton.addActionListener(onBack);

        gbc.gridy = 4;
        add(backButton, gbc);
    }

    public void setFullscreenSelected(boolean isFullscreen) {
        if (isFullscreen) {
            fullscreenRadio.setSelected(true);
        } else {
            windowedRadio.setSelected(true);
        }
    }
}
