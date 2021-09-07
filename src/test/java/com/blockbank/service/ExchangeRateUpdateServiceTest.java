package com.blockbank.service;

import com.blockbank.database.domain.Asset;
import com.blockbank.database.repository.AssetDao;

import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
@SpringBootTest
@ActiveProfiles("test")
class ExchangeRateUpdateServiceTest {

    ExchangeRateUpdateService exchangeRateUpdateService;

    private final Logger logger = LoggerFactory.getLogger(ExchangeRateUpdateServiceTest.class);
    private ExchangeRateService exchangeRateService;
    AssetDao assetDaoUnderTest;

    @Autowired
    public ExchangeRateUpdateServiceTest(AssetDao dao) {
        super();
        this.assetDaoUnderTest = dao;
        logger.info("New JdbcAssetDaoTest");
    }






   /* @Test
    void updatedAssetList() {
        List<Asset> assetList = assetDaoUnderTest.showAllAssets();
        System.out.println(assetDaoUnderTest.showAllAssets());
        //exchangeRateUpdateService.updatedAssetList(assetList);
        //System.out.println(assetList);
    }*/
}