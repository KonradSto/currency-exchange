package com.konrad.service;

import java.io.IOException;
import java.net.URL;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.konrad.model.*;
import com.konrad.utils.ArgumentValidator;
import org.springframework.stereotype.Service;

@Service
public class CurrentCurrencyExchangeService {

    private CurrentCurrencyTrade currentCurrencyTrade;

    public CurrentCurrencyExchangeService(CurrentCurrencyTrade currentCurrencyTrade) {
        ArgumentValidator.ensureNotNull(currentCurrencyTrade, "realtime currency trade");
        this.currentCurrencyTrade = currentCurrencyTrade;
    }

    public String getCurrentExchangeRate(String fromCurrency, String toCurrency) throws IOException {
        if (fromCurrency == null || toCurrency == null) {
            throw new IllegalArgumentException("Both from and to currency cannot be null");
        }
        ObjectMapper objectMapper = new ObjectMapper().configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
        currentCurrencyTrade = objectMapper.readValue(new URL("https://www.alphavantage.co/query?function=CURRENCY_EXCHANGE_RATE&from_currency="
            + fromCurrency + "&to_currency=" + toCurrency + "&apikey=IN52AZAATJWV4OQR"), CurrentCurrencyTrade.class);
        return currentCurrencyTrade.getCurrentCurrencyTradeAttributes().getExchangeRate();
    }
}
