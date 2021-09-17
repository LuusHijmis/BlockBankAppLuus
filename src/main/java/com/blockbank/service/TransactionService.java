package com.blockbank.service;
/**
 * @author Lucien
 * @author Fiona Lampers
 */

import com.blockbank.database.domain.*;
import com.blockbank.database.repository.RootRepository;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class TransactionService {
    public RootRepository rootRepository;
    public ObjectMapper mapper;
    private PortfolioService portfolioService;
    private static double TRANSACTIONS_FEE = 0.5; //TODO CHECK WHERE THIS IS IMPLEMENTED

    public static double getTransactionsFee() {
        return TRANSACTIONS_FEE;
    }

    @Autowired
    public TransactionService(RootRepository rootRepository, ObjectMapper mapper, PortfolioService portfolioService ) {
        this.rootRepository = rootRepository;
        this.mapper = mapper;
        this.portfolioService = portfolioService;
    }

    public Transaction storeTransaction(TransactionDTO transactionDTO) {
        return rootRepository.saveTransaction(transactionDTO);
    }

    public String getTransactionsJson(int userID) throws JsonProcessingException {
        List<Transaction> transactions = rootRepository.findTransactionsByUserID(userID);
        String transactionsToJson = mapper.writeValueAsString(transactions);
        return transactionsToJson;

    }

    public double transactionsCosts(TransactionDTO transactionDTO) {
        double transactionCosts = transactionDTO.getAmountAssets()
                                    * transactionDTO.getExchangeRate()
                                    * transactionDTO.getTransactionFee();
        if (transactionDTO.getOpposingUserID() > 1) {
        return transactionCosts * TRANSACTIONS_FEE;
        } else {
            return transactionCosts;
        }
    }

    // Check is User has enough funds for Buy transactions User
    public boolean accountBalanceUserSufficient(TransactionDTO transactionDTO) {
        Account account = rootRepository.findAccountByUserId(transactionDTO.getUserID());
      return accountBalanceSufficient(transactionDTO, account);
    }

    // Check is User has enough funds for Sell transactions Opposing User
    public boolean accountBalanceOpposingUserSufficient(TransactionDTO transactionDTO) {
        Account account = rootRepository.findAccountByUserId(transactionDTO.getOpposingUserID());
        return accountBalanceSufficient(transactionDTO, account);
    }

    public boolean accountBalanceSufficient(TransactionDTO transactionDTO, Account account) {
        if (account.getBalance() > transactionsCosts(transactionDTO)) {
        return true;
        } else {
            return false;
        }
    }

    public boolean portfolioBalanceSufficient(TransactionDTO transactionDTO, Map<Asset, Double> portfolio) {
        boolean approved = false;
        for (Map.Entry<Asset, Double> entry : portfolio.entrySet()) {
            if (entry.getKey().equals(transactionDTO.getAssetID())) {
                if(entry.getValue() >= transactionDTO.getAmountAssets()) {
                   return true;
                } else {
                    break;
                }
            } else {
                break;
            }
        }
        return false;
    }
    // Check if USer has enough Assets for Sell transaction User
    public boolean portfolioBalanceUserSufficient(TransactionDTO transactionDTO) {
        Map<Asset, Double> portfolioUser =  portfolioService.getAssetsTotal(transactionDTO.getUserID());
        return portfolioBalanceSufficient(transactionDTO, portfolioUser);
    }

    // Check if Opposing USer has enough Assets for Buy transaction User
    public boolean portfolioBalanceOpposingUserSufficient(TransactionDTO transactionDTO) {
            Map<Asset, Double> portfolioUser =  portfolioService.getAssetsTotal(transactionDTO.getOpposingUserID());
            return portfolioBalanceSufficient(transactionDTO, portfolioUser);
    }

    public boolean transactionApproval(TransactionDTO transactionDTO) {
        Map<Asset, Double> portfolioUser =  portfolioService.getAssetsTotal(transactionDTO.getOpposingUserID());
        Map<Asset, Double> portfolioOpposingUser =  portfolioService.getAssetsTotal(transactionDTO.getOpposingUserID());
        boolean accountBalanceSufficientUser = accountBalanceUserSufficient(transactionDTO);
        boolean accountBalanceSufficientOpposingUser = accountBalanceOpposingUserSufficient(transactionDTO);

        if (transactionDTO.getTransactionSort().equals("Buy")) {
            return transactionsValidation(transactionDTO, accountBalanceSufficientUser, portfolioOpposingUser);
        } else {
            return transactionsValidation(transactionDTO, accountBalanceSufficientOpposingUser, portfolioUser);
        }
    }

     public boolean transactionsValidation(TransactionDTO transactionDTO, boolean accountBalanceSufficient, Map<Asset, Double> portfolio ) {
        for (Map.Entry<Asset, Double> entry : portfolio.entrySet()) {
             if (entry.getKey().equals(transactionDTO.getAssetID())) {
                 if (portfolioBalanceOpposingUserSufficient(transactionDTO)) {
                     if (accountBalanceSufficient) {
                         return true;
                     } else {
                         System.out.println("There are insufficient funds available to complete this transaction");
                         return false;
                     }
                 } else {
                     System.out.println("There are insufficient assets available to complete this transaction");
                     return false;
                 }
             } else {
                 System.out.println("There are no assets available to complete this transaction");
                 return false;
             }
         }
        return false;
     }

}
