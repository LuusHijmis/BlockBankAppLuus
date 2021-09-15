package com.blockbank.service;

import com.blockbank.database.domain.Transaction;
import com.blockbank.database.domain.TransactionDTO;
import com.blockbank.database.repository.RootRepository;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TransactionService {

    public RootRepository rootRepository;
    public ObjectMapper mapper;


    @Autowired
    public TransactionService(RootRepository rootRepository, ObjectMapper mapper) {
        this.rootRepository = rootRepository;
        this.mapper = mapper;
    }

    public Transaction storeTransaction(TransactionDTO transactionDTO) {
        return rootRepository.saveTransaction(transactionDTO);
    }

    public String getTransactionsJson(int userID) throws JsonProcessingException {
        List<Transaction> transactions = rootRepository.findTransactionsByUserID(userID);
        String transactionsToJson = mapper.writeValueAsString(transactions);
        return transactionsToJson;

    }
}
