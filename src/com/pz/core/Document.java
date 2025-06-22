package com.pz.core;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;

abstract public class Document {
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
	
    public Document(
            CSV_Manager csv_manager, 
            int year, int month, int day, int number,
            String inFileName, String outFileName
    ) {
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
        BufferedReader br;
        try {
            br = new BufferedReader(new FileReader(inFileName));
        } catch (IOException ex) {
            throw new MissingFileException("Brak pliku \"" + inFileName + "\"");
        }
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
        
        String path = "documents/";
        File documents_dir = new File(path);
        if (!documents_dir.isDirectory())
            throw new MissingFileException("Brak folderu \"documents\"");
        path += year;
        File year_dir = new File(path);
        if (!year_dir.isDirectory())
            year_dir.mkdir();
        path += "/" + month;
        File month_dir = new File(path);
        int new_number = 1;
        if (!month_dir.isDirectory()) {
            month_dir.mkdir();
        } else {
            for (String document : month_dir.list()) {
                try {
                    int document_number = Integer.parseInt(document.split(".txt")[0]);
                    if (document_number >= new_number)
                        new_number = document_number + 1;
                } catch (NumberFormatException ex) {}
            }
        }
        
        FileWriter fw;
        fw = new FileWriter(path +  "/" + new_number + ".txt");
        fw.write(sb.toString());
        fw.close();        
    }
    
    abstract protected void handleTemplateVariable(int n);
}
