package com.konrad.model;

import java.util.Map;

import com.fasterxml.jackson.annotation.JsonProperty;

public class HistoricalCurrencyTradeWeekly extends HistoricalCurrencyTrade{

    @JsonProperty("Time Series FX (Weekly)")
    private Map<String, HistoricalCurrencyTradeValue> weeklyValues;

    public Map<String, HistoricalCurrencyTradeValue> getWeeklyValues() {
        return weeklyValues;
    }

    public void setWeeklyValues(Map<String, HistoricalCurrencyTradeValue> weeklyValues) {
        this.weeklyValues = weeklyValues;
    }
}
