package com.pz.core;

import com.pz.gui.Errorer;
import com.pz.gui.Invoices;
import java.io.IOException;
import javax.swing.JButton;

public class SubmitInvoice extends SubmitThread {
    private String USD_value;
    
    public SubmitInvoice(
        Errorer errorer, CSV_Manager csv_manager, Invoices invoices,
        String year, String month, String day, String number,
        JButton settlementBtn, String USD_value
    ) throws NumberFormatException {
        super(
            errorer, csv_manager, invoices, 
            year, month, day, number,
            settlementBtn
        );
        this.USD_value = USD_value;
    }
    
    @Override
    public void run() {
        try {
            Invoice invoice = new Invoice(
                csv_manager,
                year,
                month,
                day,
                number,
                USD_value
            );    
            invoice.save();
            invoices.addInvoice(
                csv_manager.addInvoice(
                    number, month, year, 
                    invoice.get_USD_value(), invoice.get_PLN_value(), 
                    invoice.getRate(), invoice.getRateName()
                )
            );
            settlementBtn.setEnabled(true);
        } catch (NumberFormatException ex) {
            errorer.show("Nieprawidłowy format danych.");
        } catch (MissingFileException ex) {
            errorer.show(ex.getMessage());
        } catch (IOException ex) {
            errorer.show("Nie udało się zapisać faktury.");
        } 
    }
}
