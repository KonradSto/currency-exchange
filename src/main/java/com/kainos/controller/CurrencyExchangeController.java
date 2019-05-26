package com.kainos.controller;

import java.io.IOException;
import java.util.List;

import com.kainos.service.CurrencyExchangeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class CurrencyExchangeController {

    private CurrencyExchangeService currencyExchangeService;

    @Autowired
    public CurrencyExchangeController(CurrencyExchangeService currencyExchangeService) {
        this.currencyExchangeService = currencyExchangeService;
    }

    @GetMapping({"/"})
    public ModelAndView showCurrencyExchangePanel(){
        ModelAndView model = new ModelAndView("home");
        String currencyCode = "";
        List<String> currencyList = currencyExchangeService.setCurrencyCodesList();
        model.addObject("currencyCode", currencyCode);
        model.addObject("currencyList", currencyList);
        return model;
    }

    @PostMapping("/exchange")
    @ResponseBody
    public ModelAndView exchangeCurrency(@RequestParam("From") String fromCurrency, @RequestParam("To") String toCurrency) throws IOException {
        ModelAndView modelAndView = new ModelAndView("exchange");
        modelAndView.addObject("From", fromCurrency);
        modelAndView.addObject("To", toCurrency);
        modelAndView.addObject("value", currencyExchangeService.getCurrentExchangeRate(fromCurrency, toCurrency));
        modelAndView.addObject("currencyMap", currencyExchangeService.getHistoricalCurrencyExchangeMap(fromCurrency, toCurrency));
        return modelAndView;
    }
}
