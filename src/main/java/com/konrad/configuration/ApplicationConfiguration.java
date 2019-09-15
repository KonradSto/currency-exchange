package com.konrad.configuration;

import com.konrad.model.HistoricalCurrencyTradeDaily;
import com.konrad.model.HistoricalCurrencyTradeMonthly;
import com.konrad.model.HistoricalCurrencyTradeWeekly;
import com.konrad.model.CurrentCurrencyTrade;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ApplicationConfiguration {

    @Bean
    public CurrentCurrencyTrade getRealtimeCurrencyTrade(){
        CurrentCurrencyTrade currentCurrencyTrade = new CurrentCurrencyTrade();
        return currentCurrencyTrade;
    }

    @Bean
    public HistoricalCurrencyTradeDaily getHistoricalCurrencyTradeDaily(){
        HistoricalCurrencyTradeDaily historicalCurrencyTradeDaily = new HistoricalCurrencyTradeDaily();
        return historicalCurrencyTradeDaily;
    }

    @Bean
    public HistoricalCurrencyTradeWeekly getHistoricalCurrencyTradeWeekly(){
        HistoricalCurrencyTradeWeekly historicalCurrencyTradeWeekly = new HistoricalCurrencyTradeWeekly();
        return historicalCurrencyTradeWeekly;
    }

    @Bean
    public HistoricalCurrencyTradeMonthly getHistoricalCurrencyTradeMonthly(){
        HistoricalCurrencyTradeMonthly historicalCurrencyTradeMonthly = new HistoricalCurrencyTradeMonthly();
        return historicalCurrencyTradeMonthly;
    }
}
