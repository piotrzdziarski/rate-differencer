package com.pz.gui;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.Insets;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class InvoiceForm extends Form {
    public InvoiceForm(String label, String btnLabel) {
        super(label, btnLabel);        
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
    }
}
