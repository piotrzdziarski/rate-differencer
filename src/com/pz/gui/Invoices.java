package com.pz.gui;

import com.pz.core.CSV_Manager;
import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.util.ArrayList;
import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class Invoices extends JPanel {
    private final Font LABEL_FONT = new Font(Font.SANS_SERIF, Font.BOLD, 16);
    private final Font FIELD_FONT = new Font(Font.SANS_SERIF, Font.BOLD, 14);
    private CSV_Manager csv_manager;
    private JPanel invoicesList;
    private JPanel emptyLabelWrapper;
    
    public Invoices(CSV_Manager csv_manager) {
        super(new BorderLayout());
        
        this.csv_manager = csv_manager;
        this.setBorder(BorderFactory.createEmptyBorder(20, 0, 25, 0));
        
        JPanel labelsWrapper = new JPanel();
        labelsWrapper.setLayout(new BoxLayout(labelsWrapper, BoxLayout.Y_AXIS));
        add(labelsWrapper, BorderLayout.NORTH);
        JPanel labels = new JPanel(new GridLayout(1, 5));
        labels.setMaximumSize(new Dimension(800, 50));
        labelsWrapper.add(labels);
        labels.add(getLabel("Numer"));
        labels.add(getLabel("Wartość (USD)"));
        labels.add(getLabel("Nazwa kursu"));
        labels.add(getLabel("Kurs"));
        labels.add(getLabel("Wartość (PLN)"));
        
        ArrayList<String[]> invoices = csv_manager.getInvoices();
        invoices.removeAll(invoices);
        invoicesList = new JPanel();
        add(invoicesList);
        invoicesList.setLayout(new BoxLayout(invoicesList, BoxLayout.Y_AXIS));
        
        emptyLabelWrapper = new JPanel(new FlowLayout());
        JLabel emptyLabel = new JLabel("Lista faktur jest pusta.");
        emptyLabel.setFont(FIELD_FONT);
        emptyLabel.setBorder(BorderFactory.createEmptyBorder(5, 0, 0, 0));
        emptyLabelWrapper.add(emptyLabel);
        invoicesList.add(emptyLabelWrapper);
        emptyLabelWrapper.setVisible(invoices.isEmpty());
        
        for (String[] invoice : invoices)
            addInvoice(invoice);
    }
    
    public void addInvoice(String[] invoice) {
        emptyLabelWrapper.setVisible(false);
        JPanel invoiceElement = new JPanel(new GridLayout(1, invoice.length));
        invoiceElement.setMaximumSize(new Dimension(800, 50));
        invoicesList.add(invoiceElement);
        for (int i = 0; i < invoice.length; i++) {
            JLabel field = new JLabel(invoice[i]);
            field.setFont(FIELD_FONT);
            JPanel invoiceElementInner = new JPanel(new FlowLayout());
            invoiceElement.add(invoiceElementInner);
            invoiceElementInner.add(field);
        }
        invoicesList.revalidate();
    }
    
    public void removeInvoices() {
        invoicesList.removeAll();
        emptyLabelWrapper.setVisible(true);
    }
    
    private JPanel getLabel(String label) {
        JPanel labelWrapper = new JPanel(new FlowLayout());
        JLabel labelElement = new JLabel(label);
        labelElement.setFont(LABEL_FONT);
        labelWrapper.add(labelElement);
        return labelWrapper;
    }
}
