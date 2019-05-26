package com.kainos.service;

import java.io.IOException;
import java.net.URL;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.kainos.model.RealtimeCurrencyTrade;
import org.springframework.stereotype.Service;

@Service
public class CurrencyExchangeService {

    private RealtimeCurrencyTrade realtimeCurrencyTrade;

    public CurrencyExchangeService(RealtimeCurrencyTrade realtimeCurrencyTrade) {
        this.realtimeCurrencyTrade = realtimeCurrencyTrade;
    }

    public String getCurrentExchangeRate(String fromCurrency, String toCurrency) throws IOException {
        if (fromCurrency == null || toCurrency == null) {
            throw new IllegalArgumentException("Both from and to currency cannot be null");
        }
        ObjectMapper objectMapper = new ObjectMapper().configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
        realtimeCurrencyTrade = objectMapper.readValue(new URL("https://www.alphavantage.co/query?function=CURRENCY_EXCHANGE_RATE&from_currency="
            + fromCurrency + "&to_currency=" + toCurrency + "&apikey=IN52AZAATJWV4OQR"), RealtimeCurrencyTrade.class);
        return realtimeCurrencyTrade.getRealtimeCurrencyTradeAttributes().getExchangeRate();
    }
}
