package com.konrad.model;

import com.fasterxml.jackson.annotation.JsonProperty;

public class HistoricalCurrencyTradeMetaData {

    @JsonProperty("1. Information")
    private String info;
    @JsonProperty("2. From Symbol")
    private String FromCurrency;
    @JsonProperty("3. To Symbol")
    private String ToCurrency;

    @Override
    public String toString() {
        return "HistoricalCurrencyTradeMetaData{" +
            "info='" + info + '\'' +
            ", FromCurrency='" + FromCurrency + '\'' +
            ", ToCurrency='" + ToCurrency + '\'' +
            '}';
    }

    public String getInfo() {
        return info;
    }

    public void setInfo(String info) {
        this.info = info;
    }

    public String getFromCurrency() {
        return FromCurrency;
    }

    public void setFromCurrency(String fromCurrency) {
        FromCurrency = fromCurrency;
    }

    public String getToCurrency() {
        return ToCurrency;
    }

    public void setToCurrency(String toCurrency) {
        ToCurrency = toCurrency;
    }
}
