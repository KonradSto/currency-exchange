package com.kainos.controller;

import com.kainos.service.CurrencyExchangeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;

class CurrencyExchangeControllerTest {

    @MockBean
    private CurrencyExchangeService currencyExchangeService;
}