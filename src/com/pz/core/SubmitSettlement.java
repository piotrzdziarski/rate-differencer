package com.pz.core;

import com.pz.gui.Errorer;
import com.pz.gui.Invoices;
import java.io.IOException;
import javax.swing.JButton;

public class SubmitSettlement extends SubmitThread {
    private String ID;
    private String rate;
    
    public SubmitSettlement(
        Errorer errorer, CSV_Manager csv_manager, Invoices invoices,
        String year, String month, String day, String number,
        JButton settlementBtn, String ID, String rate
    ) throws NumberFormatException {
        super(
            errorer, csv_manager, invoices, 
            year, month, day, number,
            settlementBtn
        );
        this.ID = ID;
        this.rate = rate;
    }
    
    @Override
    public void run() {
        try {
            Settlement settlement = new Settlement(
                csv_manager,
                year,
                month,
                day,
                number,
                ID,
                rate
            );
            settlement.save();
            invoices.removeInvoices();
            csv_manager.removeInvoices();
            settlementBtn.setEnabled(false);
        } catch (IOException ex) {
            errorer.show("Nie udało się rozliczyć faktur.");
        }
    }
}
