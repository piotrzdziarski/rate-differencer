package com.pz.gui;

import com.pz.core.CSV_Manager;
import com.pz.core.Invoice;
import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.Insets;
import java.io.IOException;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class InvoiceForm extends Form {
    public InvoiceForm(
            Errorer errorer, CSV_Manager csv_manager, Invoices invoices, 
            String label, String btnLabel
    ) {
        super(errorer, csv_manager, invoices, label, btnLabel);
    }
    
    @Override
    protected void specificForm() {
        JPanel numberValuePanelWrapper = new JPanel(new BorderLayout());       
        formInnerPanel.add(numberValuePanelWrapper, BorderLayout.CENTER);
        
        JPanel numberValueTagPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 10, 0));
        numberValuePanelWrapper.add(numberValueTagPanel, BorderLayout.NORTH);
        JLabel numberLabel = new JLabel("Numer");
        numberLabel.setFont(FORM_LABEL_FONT);
        numberLabel.setBorder(BorderFactory.createEmptyBorder(10, 0, 2, 5));
        numberValueTagPanel.add(numberLabel);
        JLabel valueLabel = new JLabel("Wartość (USD)");
        valueLabel.setFont(FORM_LABEL_FONT);
        valueLabel.setBorder(BorderFactory.createEmptyBorder(10, 0, 2, 15));
        numberValueTagPanel.add(valueLabel);
        
        JPanel numberValueButtonedWrapper = new JPanel(new BorderLayout());
        JPanel numberValuePanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 20, 3));
        numberValueButtonedWrapper.add(numberValuePanel, BorderLayout.NORTH);
        numberValuePanelWrapper.add(numberValueButtonedWrapper, BorderLayout.CENTER);
        JTextField number = new JTextField("1");
        number.setFont(INPUT_FONT);
        number.setMargin(new Insets(5, 7, 5, 5));
        numberValuePanel.add(number);
        JTextField value = new JTextField("0.00");
        value.setFont(INPUT_FONT);
        value.setMargin(new Insets(5, 7, 5, 5));
        value.setColumns(10);
        numberValuePanel.add(value);       
        
        JPanel btnWrapper = new JPanel(new FlowLayout(FlowLayout.CENTER, 0, 15));
        numberValueButtonedWrapper.add(btnWrapper, BorderLayout.CENTER);
        JButton btn = new JButton("Dodaj");
        btn.setFont(BTN_FONT);
        btnWrapper.add(btn);
        
        btn.addActionListener(e -> {
            errorer.pop();
            int year_int = Integer.parseInt(year.getText());
            int month_int = Integer.parseInt(month.getText());
            int day_int = Integer.parseInt(day.getText());
            int num_int = Integer.parseInt(number.getText());
            String USD_value = value.getText();
            
            try {
                Invoice invoice = new Invoice(
                        csv_manager,
                        year_int,
                        month_int,
                        day_int,
                        num_int,
                        USD_value
                );
                invoice.save();
                invoices.addInvoice(
                    csv_manager.addInvoice(
                        num_int, month_int, year_int, 
                        USD_value, invoice.get_PLN_value(), 
                        invoice.getRate(), invoice.getRateName()
                    )
                );
            } catch (IOException ex) {
                errorer.push("Nie udało się zapisać faktury.");
            }
        });
    }
}
