package com.blockbank.service;

import com.blockbank.database.domain.Asset;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class ExchangeRateServiceTest {

    ExchangeRateService exchangeRateService = new ExchangeRateService();
    ExchangeRateUpdateService exchangeRateUpdateService = new ExchangeRateUpdateService();


    @Test
    void getExchangeRate() {
        exchangeRateService.getExchangeRate("1","USD");
    }

    @Test
    void test() {
        List<Asset> arrayOfAssets = new ArrayList<>();
        arrayOfAssets.add(new Asset("1", "BTC", "Bitcoin", "BLABLA", 0));
        arrayOfAssets.add(new Asset("2", "Litecoin", "LTC", "BLABLA", 0));
        arrayOfAssets.add(new Asset("52", "XRP", "XRP", "BLABLA", 0));
        exchangeRateUpdateService.updatedAssetList(arrayOfAssets);

    }
}