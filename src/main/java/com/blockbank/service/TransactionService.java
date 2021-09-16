package com.blockbank.service;

import com.blockbank.database.domain.Asset;
import com.blockbank.database.domain.Transaction;
import com.blockbank.database.domain.TransactionDTO;
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

//    public boolean transactionApproval(TransactionDTO transactionDTO) {
//        boolean transactionApproved = false;
//        Asset asset = rootRepository.findAssetById(transactionDTO.getAssetID());
//        double requestedAmount = transactionDTO.getAmountAssets();
//        String TransactionSort =transactionDTO.getTransactionSort();
//        Map<Asset, Double> portfolio =  portfolioService.getAssetsTotal(transactionDTO.getUserID());
//
//        if (portfolio.containsKey(asset)) {
//            if (requestedAmount > portfolio.get(asset)) {
//
//            }
//
//
//
//
//        }
//
//
//        return transactionApproved;
//    }



}
