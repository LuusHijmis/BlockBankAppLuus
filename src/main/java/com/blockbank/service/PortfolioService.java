package com.blockbank.service;

import com.blockbank.contoller.AssetController;
import com.blockbank.database.domain.Asset;
import com.blockbank.database.domain.Transaction;
import com.blockbank.database.repository.RootRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;
import java.util.stream.Collectors;

import static com.fasterxml.jackson.databind.type.LogicalType.Map;

@Service
public class PortfolioService {

    private RootRepository rootRepository;
//    public ObjectMapper mapper;
    private final Logger logger = LoggerFactory.getLogger(PortfolioService.class);

    @Autowired
    public PortfolioService(RootRepository rootRepository) {
        this.rootRepository = rootRepository;
    }

    public Map<Asset, Double> getAssetsTotal (int userID) {
        List<Transaction> transactions = rootRepository.findTransactionsByUSerID(userID);
        Map<Asset, Double> portfolio = new HashMap<>();
        for (Transaction transaction: transactions) {
            if (portfolio.get(transaction.getAsset()) == null) {
                portfolio.put(transaction.getAsset(), transaction.getAssetAmount());
            } else {
                double currentAmount = portfolio.get(transaction.getAsset());
                if (transaction.getTransactionDescription().equals("Sell")) {
                    portfolio.replace(transaction.getAsset(), currentAmount - transaction.getAssetAmount());
                } else {
                    portfolio.replace(transaction.getAsset(), transaction.getAssetAmount() + currentAmount);
                }
            }
        }

//
//        for (Asset asset : rootRepository.showAllAssets()) {
//            if (portfolio.get(asset) == null) {
//                for (Transaction transaction : transactions) {
//                    portfolio.put(asset,transaction.getAssetAmount());
//                }
//            } else {
////                Map<Asset, Double> tempList= portfolio.get();
//                for (Transaction transaction : transactions) {
//                    double oldAssetmount = portfolio.get(transaction.getAssetAmount());
//                    portfolio.put(transaction.getAsset(), transaction.getAssetAmount() + oldAssetmount);
//                }
//            }
//        }
        return portfolio;
    }

    public double currentValueportfolio(int userID){
        double portfolioValue = 0.0;
       Map<Asset, Double> tempMap = getAssetsTotal(userID);
       for(Map.Entry<Asset,Double> entry:tempMap.entrySet()) {
           double assetAmount = entry.getValue();
           double currentExchangeRate = rootRepository.findAssetById(entry.getKey().getAssetID()).getExchangeRate();
           portfolioValue += assetAmount * currentExchangeRate;
       }
        return portfolioValue;
    }
}
