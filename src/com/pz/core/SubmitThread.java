package com.pz.core;

import com.pz.gui.Errorer;
import com.pz.gui.Invoices;
import javax.swing.JButton;

abstract public class SubmitThread extends Thread {
    protected Errorer errorer;
    protected CSV_Manager csv_manager;
    protected Invoices invoices;
    protected int year;
    protected int month;
    protected int day;
    protected int number;
    protected JButton settlementBtn;
    
    public SubmitThread(
        Errorer errorer, CSV_Manager csv_manager, Invoices invoices,
        String year, String month, String day, String number,
        JButton settlementBtn
    ) throws NumberFormatException {
        this.errorer = errorer;
        this.csv_manager = csv_manager;
        this.invoices = invoices;
        this.year = Integer.parseInt(year);
        this.month = Integer.parseInt(month);
        this.day = Integer.parseInt(day);
        this.number = Integer.parseInt(number);
        this.settlementBtn = settlementBtn;
    }
}
