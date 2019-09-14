package com.konrad.service;

import java.io.IOException;
import java.net.URL;
import java.util.LinkedHashMap;
import java.util.Map;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.konrad.model.HistoricalCurrencyTradeWeekly;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class WeeklyCurrencyExchange implements TimeRangeCurrencyExchange{

    private HistoricalCurrencyTradeWeekly historicalCurrencyTradeWeekly;

    @Autowired
    public WeeklyCurrencyExchange(HistoricalCurrencyTradeWeekly historicalCurrencyTradeWeekly) {
        this.historicalCurrencyTradeWeekly = historicalCurrencyTradeWeekly;
    }

    @Override
    public Map getHistoricalCurrencyExchangeValues(String fromCurrency, String toCurrency, String timeRange) throws IOException {
        ObjectMapper objectMapper = new ObjectMapper().configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
        Map<String, Float> weeklyCurrencyExchangeMap = new LinkedHashMap<>();
        historicalCurrencyTradeWeekly = objectMapper.readValue(new URL("https://www.alphavantage.co/query?function=FX_" + timeRange + "&from_symbol="
            + fromCurrency + "&to_symbol=" + toCurrency + "&apikey=IN52AZAATJWV4OQR"), HistoricalCurrencyTradeWeekly.class);
        for (String key : historicalCurrencyTradeWeekly.getWeeklyValues().keySet()) {
            Float value = Float.valueOf(historicalCurrencyTradeWeekly.getWeeklyValues().get(key).getValue());
            weeklyCurrencyExchangeMap.put(key, value);
        }
        return weeklyCurrencyExchangeMap;    }
}
