package com.pz.gui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Font;
import javax.swing.BorderFactory;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class Errorer extends JPanel {
    private final Font ERROR_FONT = new Font(Font.SANS_SERIF, Font.BOLD, 14);
    private JLabel error;
    
    public Errorer() {
        super(new FlowLayout());
        setBorder(BorderFactory.createEmptyBorder(0, 0, 10, 0));
        error = new JLabel();
        error.setFont(ERROR_FONT);
        error.setForeground(Color.red);
        add(error);
        setVisible(false);
    }
    
    public void push(String text) {
        error.setText(text);
        setVisible(true);
    }
    
    public void pop() {
        setVisible(false);
    }
}
