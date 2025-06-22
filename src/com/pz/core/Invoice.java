package com.pz.core;

import java.io.IOException;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Calendar;
import pl.allegro.finance.tradukisto.MoneyConverters;

public class Invoice extends Document {
    private String moneyAmount;
    private String rate;
    private String rateName;
    private String rateDate;
    private String PLN_value_with_groszy;
    private BigDecimal USD_value_with_cents;
	
    public Invoice(
            CSV_Manager csv_manager, 
            int year, int month, int day, int invoiceNumber, String moneyAmount
    ) {
        super(
                csv_manager, 
                year, month, day, invoiceNumber,
                "templates/Invoice.txt", "1.txt"
        );
	this.moneyAmount = moneyAmount;
    }
    
    public String getRate() {
        return rate;
    }
    
    public String getRateName() {
        return rateName;
    }
    
    public String get_PLN_value() {
        return PLN_value_with_groszy;
    }
    
    public String get_USD_value() {
        return USD_value_with_cents.toString();
    }

    @Override
    public void save() throws IOException {
        ExchangeRater exchangeRater = new ExchangeRater(year, month, day);
        rate = exchangeRater.getRate();
        rateName = exchangeRater.getRateName();
        rateDate = exchangeRater.getDate();
        
        USD_value_with_cents = new BigDecimal(moneyAmount)
                .setScale(2, RoundingMode.HALF_UP);
        
        double PLN_equivalent = 
                Double.parseDouble(rate) 
                * Double.parseDouble(moneyAmount);
        PLN_value_with_groszy = new BigDecimal(PLN_equivalent)
                                    .setScale(2, RoundingMode.HALF_UP)
                                    .toString();
        
        super.save();      
    }
    
    @Override
    protected void handleTemplateVariable(int templateVariableNumber) {
        switch (templateVariableNumber) {
            // Data sprzedaży
            case 0: case 4:
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
                // Termin płatności — późniejszy od daty sprzedaży o miesiąc
            case 5:
                cal.add(Calendar.MONTH, 1);
                sb.append(df.format(cal.getTime()));
                break;
            case 6: case 7: case 8: case 9:
                sb.append(USD_value_with_cents.toString());
                break;
            case 10:
                sb.append(
                        MoneyConverters.POLISH_BANKING_MONEY_VALUE.asWords(
                                USD_value_with_cents, "USD"
                        )
                );
                break;
            case 11:
                sb.append(
                        MoneyConverters.AMERICAN_ENGLISH_BANKING_MONEY_VALUE.asWords(
                                USD_value_with_cents
                        )
                );
                break;
            case 12:
                sb.append(rateName);
                break;
            case 13:
                // Data kursu walut zależy od tego jak daleko cofnęliśmy
                // się w kalendarzu aby go osiągnać.
                sb.append(rateDate);
                break;
            case 14:
                sb.append(rate);
                break;
            case 15:
                sb.append(PLN_value_with_groszy);
                break;
        }
    }
}
