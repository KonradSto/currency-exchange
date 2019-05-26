package com.kainos.model;

import com.fasterxml.jackson.annotation.JsonProperty;

public class RealtimeCurrencyTrade {

    @JsonProperty("Realtime Currency Exchange Rate")
    private RealtimeCurrencyTradeAttributes realtimeCurrencyTradeAttributes;

    public RealtimeCurrencyTradeAttributes getRealtimeCurrencyTradeAttributes() {
        return realtimeCurrencyTradeAttributes;
    }

    public void setRealtimeCurrencyTradeAttributes(RealtimeCurrencyTradeAttributes realtimeCurrencyTradeAttributes) {
        this.realtimeCurrencyTradeAttributes = realtimeCurrencyTradeAttributes;
    }
}
