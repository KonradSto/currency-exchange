package com.konrad.service;

import java.io.IOException;
import java.net.URL;
import java.util.LinkedHashMap;
import java.util.Map;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.konrad.model.HistoricalCurrencyTradeDaily;
import com.konrad.utils.ArgumentValidator;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class DailyCurrencyExchange implements TimeRangeCurrencyExchange {

    private HistoricalCurrencyTradeDaily historicalCurrencyTradeDaily;
    private static Logger log = LoggerFactory.getLogger(DailyCurrencyExchange.class);

    @Autowired
    public DailyCurrencyExchange(HistoricalCurrencyTradeDaily historicalCurrencyTradeDaily) {
        ArgumentValidator.ensureNotNull(historicalCurrencyTradeDaily, "historical currency trade daily");
        this.historicalCurrencyTradeDaily = historicalCurrencyTradeDaily;
    }

    @Override
    public Map getHistoricalCurrencyExchangeValues(String fromCurrency, String toCurrency) throws IOException {
        if (fromCurrency == null || toCurrency == null){
            log.error("Currency code was not passed and null was received instead", fromCurrency, toCurrency);
            throw new IllegalArgumentException("Neither from & to currency code cannot be null");
        }
        log.debug("Getting daily currency exchange rates for: {}", fromCurrency + " & " + toCurrency);
        ObjectMapper objectMapper = new ObjectMapper().configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
        Map<String, Float> dailyCurrencyExchangeMap = new LinkedHashMap<>();
        historicalCurrencyTradeDaily = objectMapper.readValue(new URL("https://www.alphavantage.co/query?function=FX_DAILY&from_symbol="
            + fromCurrency + "&to_symbol=" + toCurrency + "&apikey=IN52AZAATJWV4OQR"), HistoricalCurrencyTradeDaily.class);
        for (String key : historicalCurrencyTradeDaily.getDailyValues().keySet()) {
            Float value = Float.valueOf(historicalCurrencyTradeDaily.getDailyValues().get(key).getValue());
            dailyCurrencyExchangeMap.put(key, value);
        }
        return dailyCurrencyExchangeMap;
    }
}
