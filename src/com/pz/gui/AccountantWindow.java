package com.pz.gui;

import com.pz.core.CSV_Manager;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Font;
import java.io.IOException;
import javax.swing.BoxLayout;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class AccountantWindow extends JFrame {      
    public AccountantWindow() {       
        setSize(800, 475);       
        setTitle("Rozliczacz różnic kursowych");
        setVisible(true);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());
        
        Errorer errorer = new Errorer();
        add(errorer, BorderLayout.SOUTH);
        
        CSV_Manager csv_manager;
        try {
            csv_manager = new CSV_Manager();
        } catch (IOException ex) {
            errorer.push("Nie udało się odczytać pliku \"invoices.csv\".");
            return;
        }

        Invoices invoices = new Invoices(csv_manager);
        add(invoices, BorderLayout.NORTH);
        
        JPanel formsPanel = new JPanel();
        formsPanel.setLayout(new BoxLayout(formsPanel, BoxLayout.X_AXIS));
        
        add(formsPanel, BorderLayout.CENTER);
        InvoiceForm invoiceForm = new InvoiceForm(
                errorer, csv_manager, invoices, 
                "Dodaj fakturę:", "Dodaj"
        );
        formsPanel.add(invoiceForm);
        SettlementForm settlementForm = new SettlementForm(
                errorer, csv_manager, invoices, 
                "Rozlicz faktury:", "Rozlicz"
        );
        formsPanel.add(settlementForm);
        invoiceForm.setSettlementBtn(settlementForm.getBtn());
    }
}
