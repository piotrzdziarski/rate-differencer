package com.pz.gui;

import com.pz.core.CSV_Manager;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.event.ComponentEvent;
import java.awt.event.ComponentListener;
import java.awt.event.ContainerEvent;
import java.awt.event.ContainerListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.io.IOException;
import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollBar;
import javax.swing.JScrollPane;

public class AccountantWindow extends JFrame {      
    public AccountantWindow() {       
        setSize(825, 475);       
        setTitle("Rozliczacz różnic kursowych");
        setVisible(true);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        
        JPanel container = new JPanel(new BorderLayout());
        JScrollPane scroll = new JScrollPane(container);
        JScrollBar verticalScrollBar = scroll.getVerticalScrollBar();
        verticalScrollBar.addAdjustmentListener( e-> {
            int borderRight = verticalScrollBar.isShowing() 
                    ? 0 
                    : verticalScrollBar.getSize().width;
            container.setBorder(
                    BorderFactory.createEmptyBorder(0, 0, 0, borderRight + 15)
            );
        });
        add(scroll);
        
        Errorer errorer = new Errorer();
        container.add(errorer, BorderLayout.SOUTH);
        
        CSV_Manager csv_manager;
        try {
            csv_manager = new CSV_Manager();
        } catch (IOException ex) {
            errorer.push("Nie udało się odczytać pliku \"invoices.csv\".");
            return;
        }

        Invoices invoices = new Invoices(csv_manager);
        container.add(invoices, BorderLayout.NORTH);
        
        JPanel formsPanel = new JPanel();
        formsPanel.setLayout(new BoxLayout(formsPanel, BoxLayout.X_AXIS));
        
        container.add(formsPanel, BorderLayout.CENTER);
        InvoiceForm invoiceForm = new InvoiceForm(
                errorer, csv_manager, invoices, 
                "Dodaj fakturę:", "Dodaj"
        );
        invoiceForm.setBorder(BorderFactory.createEmptyBorder(0, 15, 0, 0));
        formsPanel.add(invoiceForm);
        SettlementForm settlementForm = new SettlementForm(
                errorer, csv_manager, invoices, 
                "Rozlicz faktury:", "Rozlicz"
        );
        formsPanel.add(settlementForm);
        invoiceForm.setSettlementBtn(settlementForm.getBtn());
    }
}
