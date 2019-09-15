package com.konrad.service;

import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.stereotype.Service;

@Service
public class CurrencyCodesListCreator {

    public List<String> setCurrencyCodesList() {
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
