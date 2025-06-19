package com.pz.core;

import java.io.IOException;
import java.math.BigDecimal;
import java.math.RoundingMode;
import pl.allegro.finance.tradukisto.MoneyConverters;

public class Settlement extends Document {
    private String payoutID;
    private String payoutRatio;
    private BigDecimal settledAmountDecimal;
	
    public Settlement(
            CSV_Manager csv_manager, 
            int year, int month, int day, int settlementNumber,
            String payoutID, String payoutRatio
    ) throws IOException {
        super(
                csv_manager, 
                year, month, day, settlementNumber,
                "Settlement_templatee.txt", "2.txt"
        );
	this.payoutID = payoutID;
        this.payoutRatio = payoutRatio;
    }

    @Override
    public void save() throws IOException {        
        super.save();       
    }
    
    @Override
    protected void handleTemplateVariable(int templateVariableNumber) {
        switch (templateVariableNumber) {
            case 0:
                sb.append(df.format(cal.getTime()));
                break;
            case 1:
                sb.append(accountingDocumentNumber);
                break;
            case 2:
                sb.append(month);
                break;
            case 3:
                sb.append(year);
                break;
            case 4:
                settle();
                break;
            case 5:
                sb.append(payoutRatio);
                break;
            case 6:
                sb.append(payoutID);
                break;
            case 7:
                sb.append(
                        settledAmountDecimal.doubleValue() >= 0
                                ? "Koszt"
                                : "Zysk"
                );
                break;
            case 8:
                sb.append(settledAmountDecimal);
                break;
            case 9:
                sb.append(
                    MoneyConverters.POLISH_BANKING_MONEY_VALUE.asWords(
                        settledAmountDecimal
                    )
                );
                break;
        }
    }
    
    private void settle() {
        double settledAmount = 0;
        double USD_total = 0;
        int ordinal = 1;
        for (String[] invoice : csv_manager.getInvoices()) {
            sb.append(ordinal)
              .append(". ")
              .append(invoice[0])
              .append("; wartość USD: ")
              .append(invoice[1])
              .append(", wartość PLN: ")
              .append(invoice[4])
              .append("; kurs podatkowy: ")
              .append(invoice[3])
              .append(" (")
              .append(invoice[2])
              .append(").\n");
            USD_total += Double.parseDouble(invoice[1]);
            settledAmount += Double.parseDouble(invoice[4]);
            ordinal++;
        }
        settledAmount -= USD_total * Double.parseDouble(payoutRatio);
        settledAmountDecimal = new BigDecimal(settledAmount)
                        .setScale(2, RoundingMode.HALF_UP);
    }
}
