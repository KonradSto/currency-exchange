package com.kainos.model;

import com.fasterxml.jackson.annotation.JsonProperty;

public class RealtimeCurrencyTradeAttributes {

    @JsonProperty("1. From_Currency Code")
    private String fromCurrencyCode;
    @JsonProperty("2. From_Currency Name")
    private String fromCurrencyName;
    @JsonProperty("3. To_Currency Code")
    private String toCurrencyCode;
    @JsonProperty("4. To_Currency Name")
    private String toCurrencyName;
    @JsonProperty("5. Exchange Rate")
    private String exchangeRate;

    public String getFromCurrencyCode() {
        return fromCurrencyCode;
    }

    public void setFromCurrencyCode(String fromCurrencyCode) {
        this.fromCurrencyCode = fromCurrencyCode;
    }

    public String getFromCurrencyName() {
        return fromCurrencyName;
    }

    public void setFromCurrencyName(String fromCurrencyName) {
        this.fromCurrencyName = fromCurrencyName;
    }

    public String getToCurrencyCode() {
        return toCurrencyCode;
    }

    public void setToCurrencyCode(String toCurrencyCode) {
        this.toCurrencyCode = toCurrencyCode;
    }

    public String getToCurrencyName() {
        return toCurrencyName;
    }

    public void setToCurrencyName(String toCurrencyName) {
        this.toCurrencyName = toCurrencyName;
    }

    public String getExchangeRate() {
        return exchangeRate;
    }

    public void setExchangeRate(String exchangeRate) {
        this.exchangeRate = exchangeRate;
    }
}
