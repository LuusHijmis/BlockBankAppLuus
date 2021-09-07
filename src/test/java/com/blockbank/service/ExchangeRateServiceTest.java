package com.blockbank.service;

import com.blockbank.database.domain.Asset;
import com.blockbank.database.repository.AssetDao;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
@SpringBootTest
@ActiveProfiles("test")
class ExchangeRateServiceTest {

    ExchangeRateService exchangeRateService;
    ExchangeRateUpdateService exchangeRateUpdateService;
    AssetDao assetDaoUnderTest;

    private final Logger logger = LoggerFactory.getLogger(ExchangeRateUpdateServiceTest.class);

    @Autowired
    public ExchangeRateServiceTest(ExchangeRateService exchangeRateService, ExchangeRateUpdateService exchangeRateUpdateService, AssetDao assetDaoUnderTest) {
        this.exchangeRateService = exchangeRateService;
        this.exchangeRateUpdateService = exchangeRateUpdateService;
        this.assetDaoUnderTest = assetDaoUnderTest;
    }

    @Test
    void getExchangeRate() {
        exchangeRateService.getExchangeRate("1","USD");
    }

    /*@Test
    void testPreparedList() {

        List<Asset> arrayOfAssets = new ArrayList<>();
        arrayOfAssets.add(new Asset("1", "BTC", "Bitcoin", "BLABLA", 0));
        arrayOfAssets.add(new Asset("2", "Litecoin", "LTC", "BLABLA", 0));
        arrayOfAssets.add(new Asset("52", "XRP", "XRP", "BLABLA", 0));
        exchangeRateUpdateService.updatedAssetList();

    }*/

    @Test
    void testDataBaseList() {


        exchangeRateUpdateService.updatedAssetList();

    }

}