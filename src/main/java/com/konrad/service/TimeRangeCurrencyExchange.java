package com.konrad.service;

import java.io.IOException;
import java.net.MalformedURLException;
import java.util.Map;
import java.util.Queue;

public interface TimeRangeCurrencyExchange {

    Map getHistoricalCurrencyExchangeValues(String fromCurrency, String toCurrency, String timeRange) throws IOException;
}
