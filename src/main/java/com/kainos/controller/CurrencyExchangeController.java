package com.kainos.controller;

import java.io.IOException;
import java.util.List;

import com.kainos.service.CurrencyExchangeService;
import com.kainos.utils.ArgumentValidator;
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
        ArgumentValidator.ensureNotNull(currencyExchangeService, "currency exchange service");
        this.currencyExchangeService = currencyExchangeService;
    }

    @GetMapping({"/"})
    public ModelAndView showCurrencyExchangePanel(){
        ModelAndView model = new ModelAndView("homepage");
        String currencyCode = "";
        List<String> currencyList = currencyExchangeService.setCurrencyCodesList();
        model.addObject("currencyCode", currencyCode);
        model.addObject("currencyList", currencyList);
        return model;
    }

    @PostMapping("/exchange")
    @ResponseBody
    public ModelAndView exchangeCurrency(@RequestParam("from") String fromCurrency, @RequestParam("to") String toCurrency,  @RequestParam("timeRange") String timeRange) throws IOException {
        ModelAndView modelAndView = new ModelAndView("exchange");
        modelAndView.addObject("from", fromCurrency);
        modelAndView.addObject("to", toCurrency);
        modelAndView.addObject("timeRange", timeRange);
        modelAndView.addObject("value", currencyExchangeService.getCurrentExchangeRate(fromCurrency, toCurrency));
        modelAndView.addObject("currencyMap", currencyExchangeService.getHistoricalCurrencyExchangeMap(fromCurrency, toCurrency, timeRange));
        return modelAndView;
    }
}
