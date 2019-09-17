package com.konrad.service;

import java.io.IOException;
import java.net.URL;
import java.util.LinkedHashMap;
import java.util.Map;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.konrad.model.HistoricalCurrencyTradeWeekly;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class WeeklyCurrencyExchange implements TimeRangeCurrencyExchange{

    private HistoricalCurrencyTradeWeekly historicalCurrencyTradeWeekly;
    private static Logger log = LoggerFactory.getLogger(DailyCurrencyExchange.class);

    @Autowired
    public WeeklyCurrencyExchange(HistoricalCurrencyTradeWeekly historicalCurrencyTradeWeekly) {
        this.historicalCurrencyTradeWeekly = historicalCurrencyTradeWeekly;
    }

    @Override
    public Map getHistoricalCurrencyExchangeValues(String fromCurrency, String toCurrency) throws IOException {
        log.debug("Getting weekly currency exchange rates for: {}", fromCurrency + " & " + toCurrency);
        ObjectMapper objectMapper = new ObjectMapper().configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
        Map<String, Float> weeklyCurrencyExchangeMap = new LinkedHashMap<>();
        historicalCurrencyTradeWeekly = objectMapper.readValue(new URL("https://www.alphavantage.co/query?function=FX_WEEKLY&from_symbol="
            + fromCurrency + "&to_symbol=" + toCurrency + "&apikey=IN52AZAATJWV4OQR"), HistoricalCurrencyTradeWeekly.class);
        for (String key : historicalCurrencyTradeWeekly.getWeeklyValues().keySet()) {
            Float value = Float.valueOf(historicalCurrencyTradeWeekly.getWeeklyValues().get(key).getValue());
            weeklyCurrencyExchangeMap.put(key, value);
        }
        return weeklyCurrencyExchangeMap;    }
}
