package com.pz.gui;

import com.pz.core.CSV_Manager;
import com.pz.core.Settlement;
import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.Insets;
import java.io.IOException;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class SettlementForm extends Form {
    private JButton btn;
    
    public SettlementForm(
            Errorer errorer, CSV_Manager csv_manager, Invoices invoices, 
            String label, String btnLabel
    ) {
        super(errorer, csv_manager, invoices, label, btnLabel);
    }
    
    @Override
    protected void specificForm() {
        JPanel payoutWrapper = new JPanel(new BorderLayout());     
        formInnerPanel.add(payoutWrapper, BorderLayout.CENTER);
        JPanel payoutNumbersWrapper = new JPanel(new BorderLayout());
        payoutWrapper.add(payoutNumbersWrapper, BorderLayout.NORTH);
        JPanel payoutRateWrapper = new JPanel(new BorderLayout());
        payoutWrapper.add(payoutRateWrapper, BorderLayout.CENTER);
        
        JPanel payoutTags = new JPanel(new FlowLayout(FlowLayout.CENTER, 10, 0));
        payoutNumbersWrapper.add(payoutTags, BorderLayout.NORTH);
        JLabel numberLabel = new JLabel("Numer");
        numberLabel.setFont(FORM_LABEL_FONT);
        numberLabel.setBorder(BorderFactory.createEmptyBorder(10, 0, 2, 22));
        payoutTags.add(numberLabel);
        JLabel ID_label = new JLabel("PayPal ID");
        ID_label.setFont(FORM_LABEL_FONT);
        ID_label.setBorder(BorderFactory.createEmptyBorder(10, 0, 2, 39));
        payoutTags.add(ID_label);
        
        JPanel payoutNumbersInputs = new JPanel(new BorderLayout());
        JPanel number_ID_panel = new JPanel(new FlowLayout(FlowLayout.CENTER, 20, 3));
        payoutNumbersInputs.add(number_ID_panel, BorderLayout.NORTH);
        payoutNumbersWrapper.add(payoutNumbersInputs, BorderLayout.CENTER);
        JTextField number = new JTextField("1");
        number.setFont(INPUT_FONT);
        number.setMargin(new Insets(5, 7, 5, 5));
        number_ID_panel.add(number);
        JTextField ID = new JTextField("14-");
        ID.setFont(INPUT_FONT);
        ID.setMargin(new Insets(5, 7, 5, 5));
        ID.setColumns(10);
        number_ID_panel.add(ID);
        
        JPanel payoutRateLabelWrapper = new JPanel(new FlowLayout(FlowLayout.CENTER, 0, 0));
        payoutRateWrapper.add(payoutRateLabelWrapper, BorderLayout.NORTH);
        JLabel payoutRateLabel = new JLabel("Kurs zapłaty USD/PLN:");
        payoutRateLabel.setBorder(BorderFactory.createEmptyBorder(10, 0, 2, 0));
        payoutRateLabel.setFont(FORM_LABEL_FONT);
        payoutRateLabelWrapper.add(payoutRateLabel);
        JPanel payoutRateButtonedWrapper = new JPanel(new BorderLayout());
        payoutRateWrapper.add(payoutRateButtonedWrapper, BorderLayout.CENTER);
        JPanel rateWrapper = new JPanel(new FlowLayout());
        payoutRateButtonedWrapper.add(rateWrapper, BorderLayout.NORTH);
        JTextField rate = new JTextField("3.120312");
        rate.setColumns(14);
        rate.setFont(INPUT_FONT);
        rate.setMargin(new Insets(5, 7, 5, 5));
        rateWrapper.add(rate);
        
        JPanel btnWrapper = new JPanel(new FlowLayout());
        payoutRateButtonedWrapper.add(btnWrapper, BorderLayout.CENTER);
        btnWrapper.setBorder(BorderFactory.createEmptyBorder(10, 0, 25, 0));
        btn = new JButton("Rozlicz");
        btn.setFont(BTN_FONT);
        btn.setEnabled(!csv_manager.getInvoices().isEmpty());
        btnWrapper.add(btn);
        btn.addActionListener(e -> {
            errorer.pop();
            try {
                Settlement settlement = new Settlement(
                        csv_manager,
                        Integer.parseInt(year.getText()),
                        Integer.parseInt(month.getText()),
                        Integer.parseInt(day.getText()),
                        Integer.parseInt(number.getText()),
                        ID.getText(),
                        rate.getText()
                );
                settlement.save();
                invoices.removeInvoices();
                csv_manager.removeInvoices();
                btn.setEnabled(false);
            } catch (IOException ex) {
                errorer.push("Nie udało się rozliczyć faktur.");
            }
        });
    }
    
    public JButton getBtn() {
        return btn;
    }
}
