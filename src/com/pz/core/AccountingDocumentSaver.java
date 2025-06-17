package com.pz.core;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;

abstract public class AccountingDocumentSaver {
    protected CSV_Manager csv_manager;
    protected int year;
    protected int month;
    protected int day;
    protected int accountingDocumentNumber;
    protected String inFileName;
    protected String outFileName;
    protected StringBuffer sb;
    protected Calendar cal;
    protected DateFormat df;
	
    public AccountingDocumentSaver(
            CSV_Manager csv_manager, 
            int year, int month, int day, int number,
            String inFileName, String outFileName
    ) throws IOException {
        this.csv_manager = csv_manager;
	this.year = year;
	this.month = month;
	this.day = day;
	this.accountingDocumentNumber = number;
        this.inFileName = inFileName;
        this.outFileName = outFileName;
              
        sb = new StringBuffer();
        
        cal = Calendar.getInstance();
        cal.set(year, month - 1, day);
        df = new SimpleDateFormat("yyyy-MM-dd");
    }

    protected void save() throws IOException { 
        BufferedReader br = new BufferedReader(new FileReader(inFileName));
        int templateVariableNumber = 0;
        int c;
        while ((c = br.read()) != -1) {
            char ch = (char)c;
            if (ch == '$')
                handleTemplateVariable(templateVariableNumber++);
            else
                sb.append(ch);
        }
        br.close();
        
        FileWriter fw;
        fw = new FileWriter(outFileName);
        fw.write(sb.toString());
        fw.close();        
    }
    
    abstract protected void handleTemplateVariable(int n);
}
