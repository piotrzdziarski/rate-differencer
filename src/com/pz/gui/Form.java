package com.pz.gui;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.Insets;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import javax.swing.BorderFactory;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

abstract class Form extends JPanel {
    protected final Font LABEL_FONT = new Font(Font.SANS_SERIF, Font.BOLD, 16);
    protected final Font INPUT_FONT = new Font(Font.MONOSPACED, Font.PLAIN, 18);
    protected final Font FORM_LABEL_FONT = new Font(Font.SANS_SERIF, Font.BOLD, 14);
    protected final Font BTN_FONT = new Font(Font.SANS_SERIF, Font.PLAIN, 18);
    protected String label;
    protected String btnLabel;
    private Calendar cal;
    private final DateFormat DAY = new SimpleDateFormat("dd");
    private final DateFormat MONTH = new SimpleDateFormat("MM");
    private final DateFormat YEAR = new SimpleDateFormat("yyyy");
    protected JPanel formInnerPanel;
    protected JTextField year;
    protected JTextField month;
    protected JTextField day;
    
    public Form (String label, String btnLabel) {
        super(new BorderLayout());
        this.label = label;
        this.btnLabel = btnLabel;
        this.cal = Calendar.getInstance();
        
        formInnerPanel = new JPanel(new BorderLayout());
        add(formInnerPanel, BorderLayout.CENTER);

        JPanel titleWrapper = new JPanel(new FlowLayout());
        add(titleWrapper, BorderLayout.NORTH);
        JLabel title = new JLabel(label);
        title.setBorder(BorderFactory.createEmptyBorder(0, 0, 2, 0));
        title.setFont(LABEL_FONT);
        titleWrapper.add(title);
        
        JPanel datePanelWrapper = new JPanel(new BorderLayout());       
        formInnerPanel.add(datePanelWrapper, BorderLayout.NORTH);
        
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
        day = new JTextField(getDatePart(DAY));
        day.setFont(INPUT_FONT);
        day.setMargin(new Insets(5, 7, 5, 5));
        day.setColumns(2);
        datePanel.add(day);
        month = new JTextField(getDatePart(MONTH));
        month.setFont(INPUT_FONT);
        month.setMargin(new Insets(5, 7, 5, 5));
        month.setColumns(2);
        datePanel.add(month);
        year = new JTextField(getDatePart(YEAR));
        year.setFont(INPUT_FONT);
        year.setMargin(new Insets(5, 9, 5, 7));
        datePanel.add(year);
        
        specificForm();
    }
    
    abstract protected void specificForm();
    
    private String getDatePart(DateFormat df) {
        return df.format(cal.getTime());
    }
}
