package com.blockbank.service;

import com.blockbank.database.domain.Asset;
import com.blockbank.database.repository.JdbcAssetDao;

import java.util.List;

public class ExchangeRateUpdateService {

private JdbcAssetDao jdbcAssetDao;
private ExchangeRateService exchangeRateService;


    public void updatedAssetList(List<Asset> assets){
    assets = jdbcAssetDao.showAllAssets();
        for (Asset asset : assets) {
                String assetid = asset.getAssetID();
                double koers = exchangeRateService.getExchangeRate(assetid,"USD");
                jdbcAssetDao.updateAssets(assetid,koers);
            }
    }


}


