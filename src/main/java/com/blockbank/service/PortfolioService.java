package com.blockbank.service;

import com.blockbank.database.domain.Asset;
import com.blockbank.database.domain.PortfolioDTO;
import com.blockbank.database.domain.Transaction;
import com.blockbank.database.domain.UserDetails;
import com.blockbank.database.repository.RootRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class PortfolioService {

    private RootRepository rootRepository;
    private final Logger logger = LoggerFactory.getLogger(PortfolioService.class);

    @Autowired
    public PortfolioService(RootRepository rootRepository) {
        this.rootRepository = rootRepository;
    }

    public int getUserIDbyName(String username) {
        UserDetails user = rootRepository.findUserByUsername(username);
        return user.getUserID();
    }

    public List<PortfolioDTO> generatePortfolioList(int userID){
       Map<Asset, Double> assetTotalMap = getAssetsTotal(userID);
       List<PortfolioDTO> portfolioDTOS = new ArrayList<>();
        for(Map.Entry<Asset, Double> entry :assetTotalMap.entrySet()) {
            Asset asset = entry.getKey();
            double amount = entry.getValue();
            PortfolioDTO portfolioDTO = new PortfolioDTO(asset.getAssetID(), asset.getName(), asset.getSymbol(), asset.getExchangeRate(),amount);
            portfolioDTOS.add(portfolioDTO);
        }
        return portfolioDTOS;
    }

    public Map<Asset, Double> getAssetBalanceAsUSer (int userID) {
        List<Transaction> transactions = rootRepository.findTransactionsByUserID(userID);
        return getAssetBalance(transactions);
//        Map<Asset, Double> portfolio = new HashMap<>();
//        for (Transaction transaction: transactions) {
//            if (portfolio.get(transaction.getAsset()) == null) {
//                portfolio.put(transaction.getAsset(), transaction.getAssetAmount());
//            } else {
//                double currentAmount = portfolio.get(transaction.getAsset());
//                if (transaction.getTransactionDescription().equals("Sell")) {
//                    portfolio.replace(transaction.getAsset(), currentAmount - transaction.getAssetAmount());
//                } else {
//                    portfolio.replace(transaction.getAsset(), transaction.getAssetAmount() + currentAmount);
//                }
//            }
//        }
//        return portfolio;
    }

    public Map<Asset, Double> getAssetBalanceAsOpposingUser (int userAsOpposingUserID) {
        List<Transaction> transactions = rootRepository.findTransactionByOpposingUserID(userAsOpposingUserID);
        return getAssetBalance(transactions);
//        Map<Asset, Double> portfolio = new HashMap<>();
//        for (Transaction transaction: transactions) {
//            if (portfolio.get(transaction.getAsset()) == null) {
//                portfolio.put(transaction.getAsset(), transaction.getAssetAmount());
//            } else {
//                double currentAmount = portfolio.get(transaction.getAsset());
//                if (transaction.getTransactionDescription().equals("Sell")) {
//                    portfolio.replace(transaction.getAsset(), currentAmount - transaction.getAssetAmount());
//                } else {
//                    portfolio.replace(transaction.getAsset(), transaction.getAssetAmount() + currentAmount);
//                }
//            }
//        }
//        return portfolio;
    }

    public Map<Asset, Double> getAssetBalance(List<Transaction> transactions){
        Map<Asset, Double> portfolio = new HashMap<>();
        for (Transaction transaction : transactions) {
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
        return portfolio;
    }

    //TODO CHECK OF UITKOMST CORRECT IS
    public Map<Asset, Double> getAssetsTotal(int userID) {
        Map<Asset, Double> balance = new HashMap<>();
        for (Asset asset: getAssetBalanceAsUSer(userID).keySet()){
            balance.put(asset, getAssetBalanceAsUSer(userID).get(asset) -
                    getAssetBalanceAsOpposingUser(userID).get(asset));
        }
        return balance;
    }



    public double currentValuePortfolio(int userID){
        double portfolioValue = 0.00;
       Map<Asset, Double> tempMap = getAssetsTotal(userID);
       for(Map.Entry<Asset,Double> entry:tempMap.entrySet()) {
           double assetAmount = entry.getValue();
           double currentExchangeRate = rootRepository.findAssetById(entry.getKey().getAssetID()).getExchangeRate();
           portfolioValue += assetAmount * currentExchangeRate;
       }
        return portfolioValue;
    }
}
