package com.konrad.service;

import java.io.IOException;
import java.net.URL;
import java.util.LinkedHashMap;
import java.util.Map;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.konrad.model.HistoricalCurrencyTradeDaily;
import com.konrad.utils.ArgumentValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class DailyCurrencyExchange implements TimeRangeCurrencyExchange {

    private HistoricalCurrencyTradeDaily historicalCurrencyTradeDaily;

    @Autowired
    public DailyCurrencyExchange(HistoricalCurrencyTradeDaily historicalCurrencyTradeDaily) {
        ArgumentValidator.ensureNotNull(historicalCurrencyTradeDaily, "historical currency trade daily");
        this.historicalCurrencyTradeDaily = historicalCurrencyTradeDaily;
    }

    @Override
    public Map getHistoricalCurrencyExchangeValues(String fromCurrency, String toCurrency, String timeRange) throws IOException {
        ObjectMapper objectMapper = new ObjectMapper().configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
        Map<String, Float> dailyCurrencyExchangeMap = new LinkedHashMap<>();
        historicalCurrencyTradeDaily = objectMapper.readValue(new URL("https://www.alphavantage.co/query?function=FX_" + timeRange + "&from_symbol="
            + fromCurrency + "&to_symbol=" + toCurrency + "&apikey=IN52AZAATJWV4OQR"), HistoricalCurrencyTradeDaily.class);
        for (String key : historicalCurrencyTradeDaily.getDailyValues().keySet()) {
            Float value = Float.valueOf(historicalCurrencyTradeDaily.getDailyValues().get(key).getValue());
            dailyCurrencyExchangeMap.put(key, value);
        }
        return dailyCurrencyExchangeMap;
    }
}
