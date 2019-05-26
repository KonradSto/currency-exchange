package com.kainos.service;

import java.io.IOException;
import java.net.URL;
import java.util.*;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.kainos.model.HistoricalCurrencyTradeDaily;
import com.kainos.model.RealtimeCurrencyTrade;
import org.springframework.stereotype.Service;

@Service
public class CurrencyExchangeService {

    private RealtimeCurrencyTrade realtimeCurrencyTrade;
    private HistoricalCurrencyTradeDaily historicalCurrencyTradeDaily;

    public CurrencyExchangeService(RealtimeCurrencyTrade realtimeCurrencyTrade, HistoricalCurrencyTradeDaily historicalCurrencyTradeDaily) {
        this.realtimeCurrencyTrade = realtimeCurrencyTrade;
        this.historicalCurrencyTradeDaily = historicalCurrencyTradeDaily;
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


    public List<String> setCurrencyCodesList() {
        ObjectMapper mapper = new ObjectMapper();
        Map<String, String> currencyMap = new HashMap<>();
        try {
            currencyMap = mapper.readValue(new URL(
                "https://openexchangerates.org/api/currencies.json"), new TypeReference<Map<String, String>>() {
            });

        } catch (Exception e) {
            e.printStackTrace();
        }
        return new ArrayList<>(currencyMap.keySet());
    }

/*    public String setCurrencyExchangeTimeRange(String timeRange) {

    }*/

    public Map<String, Float> getHistoricalCurrencyExchangeMap(String fromCurrency, String toCurrency) throws IOException {
        if (fromCurrency == null || toCurrency == null) {
            throw new IllegalArgumentException("Both from and to currency cannot be null");
        }
        ObjectMapper objectMapper = new ObjectMapper().configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);;
        historicalCurrencyTradeDaily = objectMapper.readValue(new URL("https://www.alphavantage.co/query?function=FX_DAILY&from_symbol="
            + fromCurrency + "&to_symbol=" + toCurrency + "&apikey=IN52AZAATJWV4OQR"), HistoricalCurrencyTradeDaily.class);
        Map<String, Float> historicalCurrencyExchangeMap = new LinkedHashMap<>();
        for (String key : historicalCurrencyTradeDaily.getDailyValues().keySet()) {
            Float value = Float.valueOf(historicalCurrencyTradeDaily.getDailyValues().get(key).getValue());
            historicalCurrencyExchangeMap.put(key, value);
        }
        return historicalCurrencyExchangeMap;
    }
}
