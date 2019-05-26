package com.kainos.configuration;

import com.kainos.model.HistoricalCurrencyTradeDaily;
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
    public HistoricalCurrencyTradeDaily getHistoricalCurrencyTrade(){
        HistoricalCurrencyTradeDaily historicalCurrencyTradeDaily = new HistoricalCurrencyTradeDaily();
        return historicalCurrencyTradeDaily;
    }
}
