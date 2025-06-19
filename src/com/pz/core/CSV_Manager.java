package com.pz.core;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class CSV_Manager {
    private final String FILE = "invoices.csv";
    private ArrayList<String[]> invoices;
    private File file;
    private Scanner sc;
    
    public CSV_Manager() throws IOException {
        invoices = new ArrayList<>();
        file = new File(FILE);
        sc = new Scanner(file);    
        
        while (sc.hasNextLine()) {
            invoices.add(sc.nextLine().split(","));
        }
        sc.close();
    }
    
    public ArrayList<String[]> getInvoices() {
        return invoices;
    }
    
    public String[] addInvoice(
            int num, int month, int year,
            String USD_val, String PLN_val,
            String rate, String rateNumber
    ) throws IOException {
        String[] invoice = {
            "FR " + num + "/" + month + "/" + year,
            USD_val,
            rateNumber,
            rate,
            PLN_val
        };
        FileWriter fw = new FileWriter("invoices.csv");   
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
        return invoice;
    }
    
    public void removeInvoices() throws IOException {
        FileWriter fw = new FileWriter(FILE);
        fw.close();
    }
}
