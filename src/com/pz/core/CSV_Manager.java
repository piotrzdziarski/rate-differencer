package com.pz.core;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class CSV_Manager {
    private ArrayList<String[]> invoices;
    private File file;
    private Scanner sc;
    
    public CSV_Manager() {
        invoices = new ArrayList<>();
        file = new File("invoices.csv");
        try {
            sc = new Scanner(file);
        } catch (FileNotFoundException ex) {}        
        
        while (sc.hasNextLine()) {
            invoices.add(sc.nextLine().split(","));
        }
        sc.close();
    }
    
    public ArrayList<String[]> getInvoices() {
        return invoices;
    }
    
    public void addInvoice(
            int num, int month, int year,
            String USD_val, String PLN_val,
            String rate, String rateNumber
    ) {
        String[] invoice = {
            "FR " + num + "/" + month + "/" + year,
            rate,
            rateNumber,
            USD_val,
            PLN_val
        };
        FileWriter fw;
        try {
            fw = new FileWriter("invoices2.csv");
        
            invoices.add(invoice);

            for (String[] inv : invoices) {
                for (int i = 0; i < inv.length; i++) {
                    fw.write(inv[i]);
                    if (i != inv.length - 1)
                        fw.write(',');
                }
                fw.write('\n');
            }
            fw.close();
        }
        catch (IOException ex) {}
    }
}
