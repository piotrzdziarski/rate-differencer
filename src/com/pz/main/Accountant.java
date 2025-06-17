package com.pz.main;

import com.pz.core.InvoiceSaver;
import com.pz.core.CSV_Manager;
import com.pz.core.SettlementSaver;
import com.pz.gui.AccountantWindow;
import java.io.IOException;
import java.math.BigDecimal;
import javax.swing.SwingUtilities;
import pl.allegro.finance.tradukisto.MoneyConverters;

public class Accountant {
    public static void main(String[] args) {  
        SwingUtilities.invokeLater(new Runnable(){
            @Override
            public void run() {
                
                //try {
                    /*CSV_Manager csv_manager = new CSV_Manager();
                    SettlementSaver ss = new SettlementSaver(
                            csv_manager,
                            2025, 6, 17, 2,
                            "142312021-1", "3.5132014"
                    );
                    ss.save();*/
                    
                    /*InvoiceSaver is = new InvoiceSaver(
                            csv_manager,
                            2025, 5, 30, 2, "272.25",
                            "Invoice_template.txt",
                            "1.txt"
                    );
                    is.save();*/
                    new AccountantWindow();
                //} catch (IOException ex) {}
            }
        });
    }
}
