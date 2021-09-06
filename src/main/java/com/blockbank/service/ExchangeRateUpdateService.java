package com.blockbank.service;

import com.blockbank.database.domain.Asset;
import com.blockbank.database.repository.JdbcAssetDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class ExchangeRateUpdateService {

JdbcAssetDao jdbcAssetDao;
ExchangeRateService exchangeRateService;


    public void updatedAssetList(List<Asset> assets){
    //assets = jdbcAssetDao.showAllAssets();

        for (Asset asset : assets) {
                asset.setExchangeRate(exchangeRateService.getExchangeRate(asset.getAssetID(),"USD"));
            System.out.println(assets);
            //jdbcAssetDao.updateAssets(asset); //deze gaat nog fout
            }
    }


}


