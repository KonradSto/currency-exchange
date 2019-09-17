package com.konrad.controller;

import java.io.IOException;
import java.util.List;

import com.konrad.service.CurrentCurrencyExchangeService;
import com.konrad.service.DailyCurrencyExchange;
import com.konrad.service.MonthlyCurrencyExchange;
import com.konrad.service.WeeklyCurrencyExchange;
import com.konrad.utils.ArgumentValidator;
import com.konrad.service.CurrencyCodesListCreator;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class CurrencyExchangeController {

    private CurrentCurrencyExchangeService currencyExchangeService;
    private DailyCurrencyExchange dailyCurrencyExchange;
    private WeeklyCurrencyExchange weeklyCurrencyExchange;
    private MonthlyCurrencyExchange monthlyCurrencyExchange;
    private CurrencyCodesListCreator currencyCodesListCreator;
    private static Logger log = LoggerFactory.getLogger(DailyCurrencyExchange.class);


    @Autowired
    public CurrencyExchangeController(CurrentCurrencyExchangeService currencyExchangeService, DailyCurrencyExchange dailyCurrencyExchange,
                                      WeeklyCurrencyExchange weeklyCurrencyExchange, MonthlyCurrencyExchange monthlyCurrencyExchange, CurrencyCodesListCreator currencyCodesListCreator) {
        ArgumentValidator.ensureNotNull(currencyExchangeService, "currency exchange service");
        this.currencyExchangeService = currencyExchangeService;
        this.dailyCurrencyExchange = dailyCurrencyExchange;
        this.weeklyCurrencyExchange = weeklyCurrencyExchange;
        this.monthlyCurrencyExchange = monthlyCurrencyExchange;
        this.currencyCodesListCreator = currencyCodesListCreator;
    }

    @GetMapping({"/"})
    public ModelAndView showCurrencyExchangePanel(){
        ModelAndView model = new ModelAndView("homepage");
        String currencyCode = "";
        List<String> currencyList = currencyCodesListCreator.setCurrencyCodesList();
        model.addObject("currencyCode", currencyCode);
        model.addObject("currencyList", currencyList);
        log.debug("Displaying currency exchange homepage");
        return model;
    }

    @PostMapping("/exchange")
    @ResponseBody
    public ModelAndView exchangeCurrency(@RequestParam("from") String fromCurrency, @RequestParam("to") String toCurrency,  @RequestParam("timeRange") String timeRange) throws IOException {
        log.debug("Processing currency exchange request");
        ModelAndView modelAndView = new ModelAndView("exchange");
        modelAndView.addObject("from", fromCurrency);
        modelAndView.addObject("to", toCurrency);
        modelAndView.addObject("timeRange", timeRange);
        modelAndView.addObject("value", currencyExchangeService.getCurrentExchangeRate(fromCurrency, toCurrency));
        switch (timeRange){
            case "DAILY":
                modelAndView.addObject("currencyMap", dailyCurrencyExchange.getHistoricalCurrencyExchangeValues(fromCurrency, toCurrency));
                break;
            case "WEEKLY":
                modelAndView.addObject("currencyMap", weeklyCurrencyExchange.getHistoricalCurrencyExchangeValues(fromCurrency, toCurrency));
                break;
            case "MONTHLY":
                modelAndView.addObject("currencyMap", monthlyCurrencyExchange.getHistoricalCurrencyExchangeValues(fromCurrency, toCurrency));
                break;
        }
        log.debug("Displaying currency exchange values");
        return modelAndView;
    }
}
