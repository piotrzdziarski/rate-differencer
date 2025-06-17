package com.pz.gui;

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
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class AccountantWindow extends JFrame {
    private final Font BTN_FONT = new Font(Font.MONOSPACED, Font.PLAIN, 20);
    private final Font FORM_LABEL_FONT = new Font(Font.SANS_SERIF, Font.BOLD, 14);
    private final DateFormat DAY = new SimpleDateFormat("dd");
    private final DateFormat MONTH = new SimpleDateFormat("MM");
    private final DateFormat YEAR = new SimpleDateFormat("yyyy");
    private Calendar cal;
    
    public AccountantWindow() {
        setSize(500, 400);       
        setTitle("Rozliczacz różnic kursowych");
        setVisible(true);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());
        
        this.cal = Calendar.getInstance();
        
        addingInvoice();
    }
    
    private void addingInvoice() {
        JPanel invoicePanel = new JPanel(new BorderLayout());
        add(invoicePanel, BorderLayout.CENTER);
        
        JPanel datePanelWrapper = new JPanel(new BorderLayout());
        invoicePanel.add(datePanelWrapper);
        JPanel dateTagPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        datePanelWrapper.add(dateTagPanel, BorderLayout.NORTH);
        JLabel dayLabel = new JLabel("Dzień");
        dayLabel.setBorder(BorderFactory.createEmptyBorder(0, 0, 0, 0));
        dayLabel.setFont(FORM_LABEL_FONT);
        dateTagPanel.add(dayLabel);
        JLabel monthLabel = new JLabel("Miesiąc");
        monthLabel.setBorder(BorderFactory.createEmptyBorder(0, 10, 0, 18));
        monthLabel.setFont(FORM_LABEL_FONT);
        dateTagPanel.add(monthLabel);
        JLabel yearLabel = new JLabel("Rok");
        yearLabel.setBorder(BorderFactory.createEmptyBorder(0, 0, 0, 28));
        yearLabel.setFont(FORM_LABEL_FONT);
        dateTagPanel.add(yearLabel);      

        JPanel datePanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 20, 3));
        datePanelWrapper.add(datePanel, BorderLayout.CENTER);
        JTextField day = new JTextField(getDatePart(DAY));
        day.setFont(BTN_FONT);
        day.setMargin(new Insets(5, 7, 5, 5));
        datePanel.add(day);
        JTextField month = new JTextField(getDatePart(MONTH));
        month.setFont(BTN_FONT);
        month.setMargin(new Insets(5, 7, 5, 5));
        datePanel.add(month);
        JTextField year = new JTextField(getDatePart(YEAR));
        year.setFont(BTN_FONT);
        year.setMargin(new Insets(5, 9, 5, 7));   
        datePanel.add(year); 
    }
    
    private String getDatePart(DateFormat df) {
        return df.format(cal.getTime());
    }
}
