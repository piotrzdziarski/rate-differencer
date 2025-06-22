package com.pz.gui;

import com.pz.core.CSV_Manager;
import java.awt.BorderLayout;
import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollBar;
import javax.swing.JScrollPane;

public class Window extends JFrame {      
    public Window() {
        setSize(825, 500);       
        setTitle("Rozliczacz różnic kursowych");
        setVisible(true);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        
        JPanel container = new JPanel(new BorderLayout());
        JScrollPane scroll = new JScrollPane(container);
        JScrollBar verticalScrollBar = scroll.getVerticalScrollBar();
        verticalScrollBar.addAdjustmentListener(e -> {
            int borderRight = verticalScrollBar.isShowing() 
                    ? 0 
                    : verticalScrollBar.getMinimumSize().width;
            container.setBorder(
                    BorderFactory.createEmptyBorder(0, 0, 0, borderRight)
            );
        });
        add(scroll);
        
        Errorer errorer = new Errorer(container);
        
        CSV_Manager csv_manager = new CSV_Manager(errorer);

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
