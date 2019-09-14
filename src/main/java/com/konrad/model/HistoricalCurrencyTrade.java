package com.konrad.model;

import com.fasterxml.jackson.annotation.JsonProperty;

public class HistoricalCurrencyTrade {

    @JsonProperty("Meta Data")
    private HistoricalCurrencyTradeMetaData historicalCurrencyTradeMetaData;

    public HistoricalCurrencyTradeMetaData getHistoricalCurrencyTradeMetaData() {
        return historicalCurrencyTradeMetaData;
    }

    public void setHistoricalCurrencyTradeMetaData(HistoricalCurrencyTradeMetaData historicalCurrencyTradeMetaData) {
        this.historicalCurrencyTradeMetaData = historicalCurrencyTradeMetaData;
    }
}
