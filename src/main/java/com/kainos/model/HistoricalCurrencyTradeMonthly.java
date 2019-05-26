package com.kainos.model;

import java.util.Map;

import com.fasterxml.jackson.annotation.JsonProperty;

public class HistoricalCurrencyTradeMonthly extends HistoricalCurrencyTrade {

    @JsonProperty("Time Series FX (Monthly)")
    private Map<String, HistoricalCurrencyTradeValue> monthlyValues;

    public Map<String, HistoricalCurrencyTradeValue> getMonthlyValues() {
        return monthlyValues;
    }

    public void setMonthlyValues(Map<String, HistoricalCurrencyTradeValue> monthlyValues) {
        this.monthlyValues = monthlyValues;
    }
}
