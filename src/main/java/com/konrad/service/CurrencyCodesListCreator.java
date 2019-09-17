package com.konrad.service;

import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

@Service
public class CurrencyCodesListCreator {

    private static Logger log = LoggerFactory.getLogger(CurrencyCodesListCreator.class);

    public List<String> setCurrencyCodesList() {
        log.debug("Creating list of codes for available currencies");
        ObjectMapper mapper = new ObjectMapper();
        Map<String, String> currencyMap = new HashMap<>();
        try {
            currencyMap = mapper.readValue(new URL(
                "https://openexchangerates.org/api/currencies.json"), new TypeReference<Map<String, String>>() {
            });

        } catch (Exception e) {
            e.printStackTrace();
        }
        return new ArrayList<>(currencyMap.keySet());
    }
}
