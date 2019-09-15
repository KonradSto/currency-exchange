package com.konrad.model;

import com.fasterxml.jackson.annotation.JsonProperty;

public class CurrentCurrencyTrade {

    @JsonProperty("Realtime Currency Exchange Rate")
    private CurrentCurrencyTradeAttributes currentCurrencyTradeAttributes;

    public CurrentCurrencyTradeAttributes getCurrentCurrencyTradeAttributes() {
        return currentCurrencyTradeAttributes;
    }

    public void setCurrentCurrencyTradeAttributes(CurrentCurrencyTradeAttributes currentCurrencyTradeAttributes) {
        this.currentCurrencyTradeAttributes = currentCurrencyTradeAttributes;
    }
}
