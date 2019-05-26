package com.kainos.service;

import java.io.IOException;
import java.net.URL;
import java.util.*;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.kainos.model.*;
import com.kainos.utils.ArgumentValidator;
import org.springframework.stereotype.Service;

@Service
public class CurrencyExchangeService {

    private RealtimeCurrencyTrade realtimeCurrencyTrade;
    private HistoricalCurrencyTradeDaily historicalCurrencyTradeDaily;
    private HistoricalCurrencyTradeWeekly historicalCurrencyTradeWeekly;
    private HistoricalCurrencyTradeMonthly historicalCurrencyTradeMonthly;

    public CurrencyExchangeService(RealtimeCurrencyTrade realtimeCurrencyTrade, HistoricalCurrencyTradeDaily historicalCurrencyTradeDaily,
                                   HistoricalCurrencyTradeWeekly historicalCurrencyTradeWeekly, HistoricalCurrencyTradeMonthly historicalCurrencyTradeMonthly) {
        ArgumentValidator.ensureNotNull(realtimeCurrencyTrade, "realtime currency trade");
        ArgumentValidator.ensureNotNull(historicalCurrencyTradeDaily, "historical currency trade daily");
        ArgumentValidator.ensureNotNull(historicalCurrencyTradeWeekly, "historical currency trade weekly");
        ArgumentValidator.ensureNotNull(historicalCurrencyTradeMonthly, "historical currency trade monthly");
        this.realtimeCurrencyTrade = realtimeCurrencyTrade;
        this.historicalCurrencyTradeDaily = historicalCurrencyTradeDaily;
        this.historicalCurrencyTradeWeekly = historicalCurrencyTradeWeekly;
        this.historicalCurrencyTradeMonthly = historicalCurrencyTradeMonthly;
    }

    public String getCurrentExchangeRate(String fromCurrency, String toCurrency) throws IOException {
        if (fromCurrency.equals(null) || toCurrency.equals(null)){
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

    public Map<String, Float> getHistoricalCurrencyExchangeMap(String fromCurrency, String toCurrency, String timeRange) throws IOException {
        ArgumentValidator.ensureNotNull(fromCurrency, "from currency");
        ArgumentValidator.ensureNotNull(toCurrency, "to currency");
        ArgumentValidator.ensureNotNull(timeRange, "time range");
        Map<String, Float> historicalCurrencyExchangeMap = new LinkedHashMap<>();
        ObjectMapper objectMapper = new ObjectMapper().configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
        switch (timeRange) {
            case "DAILY":
                historicalCurrencyExchangeMap = getDailyCurrencyExchangeMap(fromCurrency, toCurrency, timeRange, objectMapper);
                break;
            case "WEEKLY":
                historicalCurrencyExchangeMap = getWeeklyCurrencyExchangeMap(fromCurrency, toCurrency, timeRange, objectMapper);
                break;
            case "MONTHLY":
                historicalCurrencyExchangeMap = getMonthlyCurrencyExchangeMap(fromCurrency, toCurrency, timeRange, objectMapper);
                break;
        }
        return historicalCurrencyExchangeMap;
    }

    private Map<String, Float> getMonthlyCurrencyExchangeMap(String fromCurrency, String toCurrency, String timeRange, ObjectMapper objectMapper) throws IOException {
        Map<String, Float> monthlyCurrencyExchangeMap = new LinkedHashMap<>();
        historicalCurrencyTradeMonthly = objectMapper.readValue(new URL("https://www.alphavantage.co/query?function=FX_" + timeRange + "&from_symbol="
            + fromCurrency + "&to_symbol=" + toCurrency + "&apikey=IN52AZAATJWV4OQR"), HistoricalCurrencyTradeMonthly.class);
        for (String key : historicalCurrencyTradeMonthly.getMonthlyValues().keySet()) {
            Float value = Float.valueOf(historicalCurrencyTradeMonthly.getMonthlyValues().get(key).getValue());
            monthlyCurrencyExchangeMap.put(key, value);
        }
        return monthlyCurrencyExchangeMap;
    }

    private Map<String, Float> getWeeklyCurrencyExchangeMap(String fromCurrency, String toCurrency, String timeRange, ObjectMapper objectMapper) throws IOException {
        Map<String, Float> weeklyCurrencyExchangeMap = new LinkedHashMap<>();
        historicalCurrencyTradeWeekly = objectMapper.readValue(new URL("https://www.alphavantage.co/query?function=FX_" + timeRange + "&from_symbol="
            + fromCurrency + "&to_symbol=" + toCurrency + "&apikey=IN52AZAATJWV4OQR"), HistoricalCurrencyTradeWeekly.class);
        for (String key : historicalCurrencyTradeWeekly.getWeeklyValues().keySet()) {
            Float value = Float.valueOf(historicalCurrencyTradeWeekly.getWeeklyValues().get(key).getValue());
            weeklyCurrencyExchangeMap.put(key, value);
        }
        return weeklyCurrencyExchangeMap;
    }

    private Map<String, Float> getDailyCurrencyExchangeMap(String fromCurrency, String toCurrency, String timeRange, ObjectMapper objectMapper) throws IOException {
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
