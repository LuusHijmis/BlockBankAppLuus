package com.blockbank.service;

import com.blockbank.database.repository.AssetDao;

import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

@SpringBootTest
//@ActiveProfiles("test")
class ExchangeRateUpdateServiceTest {



    private final Logger logger = LoggerFactory.getLogger(ExchangeRateUpdateServiceTest.class);
    ExchangeRateService exchangeRateService;
    ExchangeRateUpdateService exchangeRateUpdateService;
    AssetDao assetDaoUnderTest;

    @Autowired
    public ExchangeRateUpdateServiceTest(ExchangeRateService exchangeRateService, ExchangeRateUpdateService exchangeRateUpdateService, AssetDao assetDaoUnderTest) {
        this.exchangeRateService = exchangeRateService;
        this.exchangeRateUpdateService = exchangeRateUpdateService;
        this.assetDaoUnderTest = assetDaoUnderTest;
    }






    @Test
    void updatedAssetList() {
       exchangeRateUpdateService.updatedAssets();
        System.out.println(assetDaoUnderTest.showAllAssets());

    }
}