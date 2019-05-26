package com.kainos.configuration;

import com.kainos.model.HistoricalCurrencyTrade;
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
    public HistoricalCurrencyTrade getHistoricalCurrencyTrade(){
        HistoricalCurrencyTrade historicalCurrencyTrade = new HistoricalCurrencyTrade();
        return historicalCurrencyTrade;
    }
}
