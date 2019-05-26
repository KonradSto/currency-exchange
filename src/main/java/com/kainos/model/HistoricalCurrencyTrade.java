package com.kainos.model;

import java.util.Map;

import com.fasterxml.jackson.annotation.JsonProperty;

public class HistoricalCurrencyTrade {

    @JsonProperty("Meta Data")
    private HistoricalCurrencyTradeMetaData historicalCurrencyTradeMetaData;

    @JsonProperty("Time Series FX (Daily)")
    private Map<String, HistoricalCurrencyTradeValue> dailyValues;

    public HistoricalCurrencyTradeMetaData getHistoricalCurrencyTradeMetaData() {
        return historicalCurrencyTradeMetaData;
    }

    public void setHistoricalCurrencyTradeMetaData(HistoricalCurrencyTradeMetaData historicalCurrencyTradeMetaData) {
        this.historicalCurrencyTradeMetaData = historicalCurrencyTradeMetaData;
    }

    public Map<String, HistoricalCurrencyTradeValue> getDailyValues() {
        return dailyValues;
    }

    public void setDailyValues(Map<String, HistoricalCurrencyTradeValue> dailyValues) {
        this.dailyValues = dailyValues;
    }

    @Override
    public String toString() {
        return "HistoricalCurrencyTrade{" +
            "historicalCurrencyTradeMetaData=" + historicalCurrencyTradeMetaData +
            ", dailyValues=" + dailyValues +
            '}';
    }
}
