package com.kainos.configuration;

import com.kainos.model.HistoricalCurrencyTradeDaily;
import com.kainos.model.HistoricalCurrencyTradeMonthly;
import com.kainos.model.HistoricalCurrencyTradeWeekly;
import com.kainos.model.RealtimeCurrencyTrade;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ApplicationConfiguration {

    @Bean
    public RealtimeCurrencyTrade getRealtimeCurrencyTrade(){
        RealtimeCurrencyTrade realtimeCurrencyTrade = new RealtimeCurrencyTrade();
        return realtimeCurrencyTrade;
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
