package com.konrad.model;

import java.util.Map;

import com.fasterxml.jackson.annotation.JsonProperty;

public class HistoricalCurrencyTradeDaily extends HistoricalCurrencyTrade{

    @JsonProperty("Time Series FX (Daily)")
    private Map<String, HistoricalCurrencyTradeValue> dailyValues;

    public Map<String, HistoricalCurrencyTradeValue> getDailyValues() {
        return dailyValues;
    }

    public void setDailyValues(Map<String, HistoricalCurrencyTradeValue> dailyValues) {
        this.dailyValues = dailyValues;
    }
}
