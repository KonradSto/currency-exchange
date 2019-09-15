package com.konrad.service;

import java.io.IOException;
import java.net.URL;
import java.util.LinkedHashMap;
import java.util.Map;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.konrad.model.HistoricalCurrencyTradeMonthly;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class MonthlyCurrencyExchange implements TimeRangeCurrencyExchange {

    private HistoricalCurrencyTradeMonthly historicalCurrencyTradeMonthly;

    @Autowired
    public MonthlyCurrencyExchange(HistoricalCurrencyTradeMonthly historicalCurrencyTradeMonthly) {
        this.historicalCurrencyTradeMonthly = historicalCurrencyTradeMonthly;
    }

    @Override
    public Map getHistoricalCurrencyExchangeValues(String fromCurrency, String toCurrency, String timeRange) throws IOException {
        ObjectMapper objectMapper = new ObjectMapper().configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
        Map<String, Float> monthlyCurrencyExchangeMap = new LinkedHashMap<>();
        historicalCurrencyTradeMonthly = objectMapper.readValue(new URL("https://www.alphavantage.co/query?function=FX_" + timeRange + "&from_symbol="
            + fromCurrency + "&to_symbol=" + toCurrency + "&apikey=IN52AZAATJWV4OQR"), HistoricalCurrencyTradeMonthly.class);
        for (String key : historicalCurrencyTradeMonthly.getMonthlyValues().keySet()) {
            Float value = Float.valueOf(historicalCurrencyTradeMonthly.getMonthlyValues().get(key).getValue());
            monthlyCurrencyExchangeMap.put(key, value);
        }
        return monthlyCurrencyExchangeMap;
    }
}
