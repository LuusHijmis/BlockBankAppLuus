package com.blockbank.database.repository;

import com.blockbank.database.domain.Transaction;

import java.util.List;

public interface TransactionDao {

    Transaction save (Transaction transaction);
    Transaction delete(Transaction transaction);
    List<Transaction> findByUserId(int transactionID);
}
