package com.pz.core;

import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;

public class ExchangeRater {
    private final String URL_TEMPLATE = "http://api.nbp.pl/api/exchangerates/rates/a/usd/";
    private DateFormat dateFormat;
    private Calendar cal;
    private String cutJSONResponse;
    
    public ExchangeRater(int year, int month, int day) {
        dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        cal = Calendar.getInstance();
        cal.set(year, month - 1, day);
        storeResponse();
    }
    
    private void storeResponse() {
        // Zgodnie z polskim prawem, aby zaksięgować fakturę w walucie obcej,
        // trzeba ją przeliczyć na złotówki po kursie z dnia roboczego bezpośrednio
        // poprzedzającego dzień wystawienia faktury.
        this.cal.add(Calendar.DATE, -1);
        URL url;
        try {
            String YMD_date = dateFormat.format(cal.getTime());
            url = new URL(URL_TEMPLATE + YMD_date + "/");
        } catch (MalformedURLException ex) {
            return;
        }
        try {
            URLConnection ucon = url.openConnection();
            ucon.connect();
            String status = ucon.getHeaderField(0).substring(9, 12);

            // NBP oblicza kursy walut tylko w dni robocze, więc jeśli trafiamy
            // dzień wolny od pracy, cofamy się w kalendarzu aż trafimy dzień roboczy.
            if (!status.equals("200")) {
                storeResponse();
            } else {
                InputStream is = ucon.getInputStream();
                String JSONResponse = new String(is.readAllBytes());
                this.cutJSONResponse = JSONResponse.split("no\":\"")[1];
            }
        } catch (IOException ex) {}
    }
    
    public String getDate() {
        return dateFormat.format(cal.getTime());
    }
    
    public String getRateName() {
        return cutJSONResponse.split("\"")[0];
    }
    
    public String getRate() {
        return cutJSONResponse.split("mid\":")[1].substring(0, 6);
    }
}
