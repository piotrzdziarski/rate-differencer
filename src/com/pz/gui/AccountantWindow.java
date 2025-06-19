package com.pz.gui;

import com.pz.core.CSV_Manager;
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
        CSV_Manager csv_manager = new CSV_Manager();
        
        setSize(800, 475);       
        setTitle("Rozliczacz różnic kursowych");
        setVisible(true);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());
                
        add(new Invoices(csv_manager), BorderLayout.NORTH);
        
        JPanel formsPanel = new JPanel();
        formsPanel.setLayout(new BoxLayout(formsPanel, BoxLayout.X_AXIS));
        
        add(formsPanel, BorderLayout.CENTER);
        Form invoiceForm = new InvoiceForm("Dodaj fakturę:", "Dodaj");
        formsPanel.add(invoiceForm);
        Form settlementForm = new SettlementForm("Rozlicz faktury:", "Rozlicz");
        formsPanel.add(settlementForm);
    }
}
