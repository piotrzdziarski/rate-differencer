package com.pz.gui;

import javax.swing.JPanel;
import javax.swing.JOptionPane;

public class Errorer {
    private JPanel container;
    
    public Errorer(JPanel container) {
        this.container = container;
    }
    
    public void show(String message) {
        JOptionPane.showMessageDialog(
            container, 
            message, 
            "Błąd", 
            JOptionPane.ERROR_MESSAGE
        );
    }
}
