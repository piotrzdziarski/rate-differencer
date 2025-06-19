package com.pz.gui;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.Insets;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class AccountantWindow extends JFrame {  
    public AccountantWindow() {
        setSize(500, 400);       
        setTitle("Rozliczacz różnic kursowych");
        setVisible(true);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());
        
        JPanel formsPanel = new JPanel();
        BoxLayout bl = new BoxLayout(formsPanel, BoxLayout.X_AXIS);
        formsPanel.setLayout(bl);
        
        add(formsPanel, BorderLayout.CENTER);
        Form invoiceForm = new Form(formsPanel, "Dodaj fakturę:", "Dodaj");
        invoiceForm.invoice();
        Form settlementForm = new Form(formsPanel, "Rozlicz:", "Rozlicz");
        settlementForm.settlement();
    }
}
