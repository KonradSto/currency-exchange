package com.konrad.service;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.*;

import java.io.IOException;

import com.konrad.model.CurrentCurrencyTrade;
import com.konrad.model.CurrentCurrencyTradeAttributes;
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
class CurrentCurrencyExchangeServiceTest {

    @InjectMocks
    private CurrentCurrencyExchangeService currencyExchangeService;

    @Mock
    CurrentCurrencyTrade currentCurrencyTrade;

    private String fromCurrency = "USD";
    private String toCurrency = "EUR";

    @Disabled
    @Test
    void shouldReturnCurrentExchangeRate() throws IOException {
        //Given
        String expectedExchangeRate = "1.5";
        CurrentCurrencyTradeAttributes currentCurrencyTradeAttributes = mock(CurrentCurrencyTradeAttributes.class);
        when(currentCurrencyTradeAttributes.getExchangeRate()).thenReturn(expectedExchangeRate);
        when(currentCurrencyTrade.getCurrentCurrencyTradeAttributes()).thenReturn(currentCurrencyTradeAttributes);

        //When
        String currencyExchangeResult = currencyExchangeService.getCurrentExchangeRate(fromCurrency, toCurrency);

        //Then
        assertEquals(expectedExchangeRate, currencyExchangeResult);
        verify(currentCurrencyTrade.getCurrentCurrencyTradeAttributes()).getExchangeRate();
    }
}

