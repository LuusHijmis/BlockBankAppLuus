package com.blockbank.service;

import com.blockbank.database.domain.Asset;
import com.blockbank.database.repository.AssetDao;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
@Service
@Configuration
@EnableScheduling
public class ExchangeRateUpdateService {

AssetDao assetDao;
ExchangeRateService exchangeRateService;

private final Logger logger = LoggerFactory.getLogger(ExchangeRateService.class);


@Autowired
    public ExchangeRateUpdateService(AssetDao assetDao, ExchangeRateService exchangeRateService) {
        this.assetDao = assetDao;
        this.exchangeRateService = exchangeRateService;
        logger.info("New ExchangeRateUpdateService");
    }

    public List<Asset> updatedAssetList(){
    List<Asset> assets = assetDao.showAllAssets();
    List<Asset> updatedAssetsList = new ArrayList<>();

        for (Asset asset : assets) {
                asset.setExchangeRate(exchangeRateService.getExchangeRate(asset.getAssetID()));
            updatedAssetsList.add(asset);
            }
        return updatedAssetsList;

    }
    //TODO fout to many request afvangen?
    @Scheduled(cron = "0 0 0/2 * * ?")
    public void updatedAssets(){
        List<Asset> assets = updatedAssetList();
        for (Asset asset : assets){
            assetDao.updateAsset(asset);
        }
    }
}


