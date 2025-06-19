package com.pz.gui;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.Insets;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class Form {
    protected final Font LABEL_FONT = new Font(Font.SANS_SERIF, Font.BOLD, 16);
    protected final Font INPUT_FONT = new Font(Font.MONOSPACED, Font.PLAIN, 18);
    protected final Font FORM_LABEL_FONT = new Font(Font.SANS_SERIF, Font.BOLD, 14);
    protected final Font BTN_FONT = new Font(Font.SANS_SERIF, Font.PLAIN, 18);
    private Calendar cal;
    private final DateFormat DAY = new SimpleDateFormat("dd");
    private final DateFormat MONTH = new SimpleDateFormat("MM");
    private final DateFormat YEAR = new SimpleDateFormat("yyyy");
    protected JPanel invoiceInnerPanel;
    
    public Form (JPanel formsPanel, String label, String btnLabel) {        
        this.cal = Calendar.getInstance();
        
        JPanel invoicePanel = new JPanel(new BorderLayout());
        invoiceInnerPanel = new JPanel(new BorderLayout());
        formsPanel.add(invoicePanel);
        invoicePanel.add(invoiceInnerPanel, BorderLayout.CENTER);

        JPanel titleWrapper = new JPanel(new FlowLayout());
        invoicePanel.add(titleWrapper, BorderLayout.NORTH);
        JLabel title = new JLabel(label);
        title.setBorder(BorderFactory.createEmptyBorder(0, 0, 2, 0));
        title.setFont(LABEL_FONT);
        titleWrapper.add(title);
        
        JPanel datePanelWrapper = new JPanel(new BorderLayout());       
        invoiceInnerPanel.add(datePanelWrapper, BorderLayout.NORTH);
        
        JPanel dateTagPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        datePanelWrapper.add(dateTagPanel, BorderLayout.NORTH);
        JLabel dayLabel = new JLabel("Dzień");
        dayLabel.setBorder(BorderFactory.createEmptyBorder(0, 5, 0, 0));
        dayLabel.setFont(FORM_LABEL_FONT);
        dateTagPanel.add(dayLabel);
        JLabel monthLabel = new JLabel("Miesiąc");
        monthLabel.setBorder(BorderFactory.createEmptyBorder(0, 8, 0, 18));
        monthLabel.setFont(FORM_LABEL_FONT);
        dateTagPanel.add(monthLabel);
        JLabel yearLabel = new JLabel("Rok");
        yearLabel.setBorder(BorderFactory.createEmptyBorder(0, 0, 0, 28));
        yearLabel.setFont(FORM_LABEL_FONT);
        dateTagPanel.add(yearLabel);      

        JPanel datePanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 20, 0));
        datePanelWrapper.add(datePanel, BorderLayout.CENTER);
        JTextField day = new JTextField(getDatePart(DAY));
        day.setFont(INPUT_FONT);
        day.setMargin(new Insets(5, 7, 5, 5));
        day.setColumns(2);
        datePanel.add(day);
        JTextField month = new JTextField(getDatePart(MONTH));
        month.setFont(INPUT_FONT);
        month.setMargin(new Insets(5, 7, 5, 5));
        month.setColumns(2);
        datePanel.add(month);
        JTextField year = new JTextField(getDatePart(YEAR));
        year.setFont(INPUT_FONT);
        year.setMargin(new Insets(5, 9, 5, 7));
        datePanel.add(year); 
    }
    
    public void invoice() {
        JPanel numberValuePanelWrapper = new JPanel(new BorderLayout());       
        invoiceInnerPanel.add(numberValuePanelWrapper, BorderLayout.CENTER);
        
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
    
    public void settlement() {
        JPanel payoutWrapper = new JPanel(new BorderLayout());     
        invoiceInnerPanel.add(payoutWrapper, BorderLayout.CENTER);
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
    
    private String getDatePart(DateFormat df) {
        return df.format(cal.getTime());
    }
}
