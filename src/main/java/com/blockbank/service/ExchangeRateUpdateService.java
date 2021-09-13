package com.blockbank.service;

import com.blockbank.database.domain.Asset;
import com.blockbank.database.repository.AssetDao;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
@Service
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
            //System.out.println(assets);
            //jdbcAssetDao.updateAssets(asset); //deze gaat nog fout
            updatedAssetsList.add(asset);
            }
        return updatedAssetsList;

    }

    public void updatedAssets(){
        List<Asset> assets = updatedAssetList();
        for (Asset asset : assets){
            assetDao.updateAsset(asset);
        }
    }
}


