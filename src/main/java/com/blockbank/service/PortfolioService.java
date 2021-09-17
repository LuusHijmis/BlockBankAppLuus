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
import java.util.stream.Collectors;
import java.util.stream.Stream;


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

    public Map<Asset, Double> getAssetsTotal (int userID) {
        List<Transaction> newList = totalTransactionListForUser(userID);

        Map<Asset, Double> portfolio = new HashMap<>();
        for (Transaction transaction : newList) {
            if (transaction.getUserDetails().getUserID() == userID) {
                // TODO Asset in Portfolio mag niet NULL zijn bij een Sell transactie
                if (portfolio.get(transaction.getAsset()) == null) {
                    portfolio.put(transaction.getAsset(), transaction.getAssetAmount());
//                    System.out.println("USER: " + transaction.getTransactionDescription() + " " + transaction.getAssetAmount() + " " + transaction.getAsset().getName());
                } else {
                    double currentAmount = portfolio.get(transaction.getAsset());
                    if (transaction.getTransactionDescription().equals("Sell")) {
                        portfolio.replace(transaction.getAsset(), currentAmount - transaction.getAssetAmount());
//                        System.out.println("USER: " + transaction.getTransactionDescription() + " " + transaction.getAssetAmount() + " " + transaction.getAsset().getName());
                    } else {
                        portfolio.replace(transaction.getAsset(), currentAmount + transaction.getAssetAmount());
                    }
                }
            } else {
                // TODO Asset in Portfolio mag niet NULL zijn bij een Buy transactie
                if (portfolio.get(transaction.getAsset()) == null) {
                    portfolio.put(transaction.getAsset(), transaction.getAssetAmount());
                } else {
                    double currentAmount = portfolio.get(transaction.getAsset());
                    if (transaction.getTransactionDescription().equals("Sell")) {
                        portfolio.replace(transaction.getAsset(), currentAmount + transaction.getAssetAmount());
//                        System.out.println("OPP-USER " + transaction.getTransactionDescription() + " " + transaction.getAssetAmount() + " " + transaction.getAsset().getName());
                    } else {
                        portfolio.replace(transaction.getAsset(), currentAmount - transaction.getAssetAmount());
//                        System.out.println("OPP-USER " + transaction.getTransactionDescription() + " " + transaction.getAssetAmount() + " " + transaction.getAsset().getName());
                    }
                }
            }
        }
        return portfolio;
    }

    public List<Transaction> totalTransactionListForUser(int userID) {
        List<Transaction> transactions1 = rootRepository.findTransactionsByUserID(userID);
        List<Transaction> transactions2 = rootRepository.findTransactionByOpposingUserID(userID);
        if (transactions1 == null) {
            return transactions2;
        }
        if (transactions2 == null) {
            return transactions1;
        } else {
            return Stream.concat(transactions1.stream(), transactions2.stream())
                    .collect(Collectors.toList());
        }
    }

    public double currentValuePortfolio(int userID) {
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
