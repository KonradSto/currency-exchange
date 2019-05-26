package com.kainos.service;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.*;

import java.io.IOException;

import com.kainos.model.RealtimeCurrencyTrade;
import com.kainos.model.RealtimeCurrencyTradeAttributes;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
@RunWith(MockitoJUnitRunner.class)
class CurrencyExchangeServiceTest {

    @InjectMocks
    private CurrencyExchangeService currencyExchangeService;

    @Mock
    RealtimeCurrencyTrade realtimeCurrencyTrade;

    private String fromCurrency = "USD";
    private String toCurrency = "EUR";

    @Disabled
    @Test
    void shouldReturnCurrentExchangeRate() throws IOException {
        //Given
        String expectedExchangeRate = "1.5";
        RealtimeCurrencyTradeAttributes realtimeCurrencyTradeAttributes = mock(RealtimeCurrencyTradeAttributes.class);
        when(realtimeCurrencyTradeAttributes.getExchangeRate()).thenReturn(expectedExchangeRate);
        when(realtimeCurrencyTrade.getRealtimeCurrencyTradeAttributes()).thenReturn(realtimeCurrencyTradeAttributes);

        //When
        String currencyExchangeResult = currencyExchangeService.getCurrentExchangeRate(fromCurrency, toCurrency);

        //Then
        assertEquals(expectedExchangeRate, currencyExchangeResult);
        verify(realtimeCurrencyTrade.getRealtimeCurrencyTradeAttributes()).getExchangeRate();
    }
}

