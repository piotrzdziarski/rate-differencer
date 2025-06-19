package com.pz.gui;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.Insets;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class SettlementForm extends Form {
    public SettlementForm(String label, String btnLabel) {
        super(label, btnLabel);        
    }
    
    @Override
    protected void specificForm() {
        JPanel payoutWrapper = new JPanel(new BorderLayout());     
        formInnerPanel.add(payoutWrapper, BorderLayout.CENTER);
        JPanel payoutIDWrapper = new JPanel(new BorderLayout());
        payoutWrapper.add(payoutIDWrapper, BorderLayout.NORTH);
        JPanel payoutRateWrapper = new JPanel(new BorderLayout());
        payoutWrapper.add(payoutRateWrapper, BorderLayout.CENTER);
        
        JPanel payoutIDLabelWrapper = new JPanel(new FlowLayout(FlowLayout.CENTER, 0, 0));
        payoutIDWrapper.add(payoutIDLabelWrapper, BorderLayout.NORTH);
        JLabel payoutIDLabel = new JLabel("PayPal ID:");
        payoutIDLabel.setBorder(BorderFactory.createEmptyBorder(10, 0, 2, 0));
        payoutIDLabel.setFont(FORM_LABEL_FONT);
        payoutIDLabelWrapper.add(payoutIDLabel);
        JPanel IDWrapper = new JPanel(new FlowLayout());
        payoutIDWrapper.add(IDWrapper, BorderLayout.CENTER);
        JTextField ID = new JTextField("14-");
        ID.setColumns(14);
        ID.setFont(INPUT_FONT);
        ID.setMargin(new Insets(5, 7, 5, 5));
        IDWrapper.add(ID);
        
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
        
        JPanel btnWrapper = new JPanel(new FlowLayout(FlowLayout.CENTER, 0, 12));
        payoutRateButtonedWrapper.add(btnWrapper, BorderLayout.CENTER);
        JButton btn = new JButton("Rozlicz");
        btn.setFont(BTN_FONT);
        btnWrapper.add(btn);
    }
}
