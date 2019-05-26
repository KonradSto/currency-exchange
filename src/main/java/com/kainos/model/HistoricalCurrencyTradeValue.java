package com.kainos.model;

import com.fasterxml.jackson.annotation.JsonProperty;

public class HistoricalCurrencyTradeValue {

    @JsonProperty("4. close")
    private String value;

    @Override
    public String toString() {
        return "HistoricalCurrencyTradeValue{" +
            "value='" + value + '\'' +
            '}';
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }
}
